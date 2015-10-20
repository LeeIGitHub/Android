package lee.afk.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;
import lee.afk.afkutils.log.LeeLog;

public class SlidingLayout extends FrameLayout {
	/**
	 * 只能向左滑动
	 */
	public final static int SLIDING_LEFT = 0x001;
	/**
	 * 只能向右滑动
	 */
	public final static int SLIDING_RIGHT = 0x002;
	/**
	 * 左右都能滑动
	 */
	public final static int SLIDING_LEFT_RIGHT = 0x003;

	public interface SildingListener {
		boolean onSilding(boolean isSildingOut);
	}

	private SildingListener mSildingListener;

	/**
	 * 用于记录用户按下去是的坐标X
	 */
	private float oldX;

	private float oldY;

	/**
	 * 用于记录滑动距离
	 */
	private float scrollX;

	/**
	 * 用于记录上一次的滑动距离
	 */
	private float oldScrollX;
	
	/**
	 * 记录当手指up的时候的X速度
	 */
	private float mXSpeedActionUp;
	/**
	 * 获取父类的ViewGroup
	 */
	private View mContentView;

	/**
	 * 父类mContentView 宽度的一半，主要用作与判断用户是否滑动超过了一半的一个分界线
	 */
	private float mBounds;

	private int mPointerId;

	/**
	 * 滑动类
	 */
	private Scroller mScroller;

	/**
	 * 获取手指滑动速度类
	 */
	private VelocityTracker mVelocityTracker;

	/**
	 * 可滑动的方向，默认左右都可以
	 */
	private int mSlideOrientation = SLIDING_LEFT_RIGHT;

	public SlidingLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SlidingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		initScroller();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);

		mContentView = (View) this.getParent();
		mBounds = mContentView.getWidth() / 2;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		initVelocityTracker(event);
		mVelocityTracker.addMovement(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mPointerId = event.getPointerId(0);
			scrollX = 0;
			mScroller.abortAnimation();
			oldX = event.getX();
			oldY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			actionMove(event);
			break;
		case MotionEvent.ACTION_UP:
			actionUp(event);
			break;
		case MotionEvent.ACTION_CANCEL:
			actionCancle(event);
			break;
		default:
			break;
		}
		return true;
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			mContentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
			// LeeLog.p("currX " + mScroller.getCurrX());

			if (Math.abs(mScroller.getCurrX()) >= getWidth()) {
				if (mSildingListener != null) {
					mSildingListener.onSilding(true);
				}
			}

			if (mScroller.isFinished()) {
				LeeLog.p("mScroller.isFinished()");
			}
		}
	}

	private void actionUp(MotionEvent event) {
		mXSpeedActionUp = Math.abs(getXSpeed());
		if (isOutOfBounds()) {
			scrollOut();
		} else {
			scrollRestore();
		}
		releaseVelocityTracker();
	}

	private void actionMove(MotionEvent event) {
		if (canScroll(event)) {
			oldScrollX = scrollX;
			int sildX = (int) (oldX - event.getX());
			scrollX += sildX;
			LeeLog.p("scrollX " + scrollX);
			if (mSlideOrientation == SLIDING_LEFT) {
				if (scrollX < 0) {
					sildX = (int) -oldScrollX;
					scrollX = 0;
				}
			} else if (mSlideOrientation == SLIDING_RIGHT) {
				if (scrollX > 0) {
					sildX = (int) -oldScrollX;
					scrollX = 0;
				}
			}
			mContentView.scrollBy(sildX, 0);
		}
	}
	
	private void actionCancle(MotionEvent event){
		releaseVelocityTracker();
	}

	/**
	 * 判断是否可以滑动
	 * 
	 * @param event
	 * @return
	 */
	private boolean canScroll(MotionEvent event) {
		// LeeLog.p("content scrollX" + mContentView.getScrollX());
		switch (mSlideOrientation) {
		case SLIDING_LEFT:
			if (mContentView.getScrollX() == 0)
				return (event.getX() - oldX) < 0;
			return mContentView.getScrollX() > 0;
		case SLIDING_RIGHT:
			if (mContentView.getScrollX() == 0)
				return (event.getX() - oldX) > 0;
			return mContentView.getScrollX() < 0;
		case SLIDING_LEFT_RIGHT:
			return true;
		default:
			return true;
		}
	}

	/**
	 * 判断用户是往左滑动还是往右滑动 若为左边滑动，返回true
	 * 
	 * @param event
	 * @return
	 */
	private boolean userMoveToLeft(MotionEvent event) {
		if (oldX - event.getX() > 0)
			return false;
		else
			return true;
	}

	private boolean isLeft() {
		return Math.abs(mContentView.getScrollX()) < mBounds;
	}

	/**
	 * 是否滑出了界线，目前界线设定为中心
	 * 
	 * @return
	 */
	private boolean isOutOfBounds() {
		// LeeLog.p("getScrollX " + mContentView.getScrollX() + " mBounds" +
		// mBounds);
		mXSpeedActionUp = 0;
		return Math.abs(mContentView.getScrollX() + mXSpeedActionUp) - mBounds > 0;
	}

	/**
	 * 开始滑到中间，复原，并随着滑行动画
	 */
	private void scrollRestore() {
		int scrollX = mContentView.getScrollX();
		mScroller.startScroll(scrollX, 0, -scrollX, 0, Math.abs(scrollX));
		postInvalidate();
	}

	/**
	 * 开始滑出去，并随着滑行动画
	 */
	private void scrollOut() {
		int dx = 0;
		if (scrollX > 0)
			dx = getWidth() - (int) scrollX;
		else
			dx = (int) -(mContentView.getWidth() + scrollX);
		
//		int duration = (int) Math.abs((-scrollX - scrollX) / mXSpeedActionUp * 50);
//		LeeLog.p("duration  " + duration + "   speed " + mXSpeedActionUp
//				+"   lucheng  " + ((-scrollX - scrollX) / mXSpeedActionUp));
		
		mScroller.startScroll((int) scrollX, 0, dx, 0, Math.abs(dx));
//		mScroller.startScroll((int) scrollX, 0, dx, 0, duration);
		postInvalidate();
	}

	/**
	 * 初始化Scroller
	 */
	private void initScroller() {
		if (mScroller == null)
			mScroller = new Scroller(getContext());
	}

	private void initVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	private void releaseVelocityTracker() {
		if (mVelocityTracker != null) {
			mVelocityTracker.clear();
			mVelocityTracker.recycle();
			mVelocityTracker = null;
		}
	}

	
	private float getXSpeed() {
		mVelocityTracker.computeCurrentVelocity(1000,
				ViewConfiguration.get(getContext()).getScaledMaximumFlingVelocity());
		return mVelocityTracker.getXVelocity(mPointerId);
	}

	/**
	 * 设置可滑动的方向
	 * 
	 * @param orientation
	 */
	public void setScrollOrientation(int orientation) {
		mSlideOrientation = orientation;
	}

	/**
	 * 设置滑动监听
	 * 
	 * @param listener
	 */
	public void setOnSlidingListener(SildingListener listener) {
		this.mSildingListener = listener;
	}

	/**
	 * 设置ContentView
	 * 
	 * @param v
	 */
	public void setContentView(View v) {
		this.mContentView = (View) v.getParent();
	}
}
