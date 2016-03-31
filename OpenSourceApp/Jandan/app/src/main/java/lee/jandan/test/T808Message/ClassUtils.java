 package lee.jandan.test.T808Message;
 
// import org.apache.log4j.Logger;

 import lee.afk.afkutils.log.LeeLog;

 public class ClassUtils
 {
//   public static Logger logger = Logger.getLogger(ClassUtils.class);
 
   public static Object getBean(String className)
   {
     Class clazz = null;
     try
     {
       clazz = Class.forName(className);
     }
     catch (Exception ex)
     {
//       logger.info("�Ҳ���ָ������");
       LeeLog.e(ex);
       LeeLog.e("找不到指定的类");
     }
     if (clazz != null)
     {
       try
       {
         return clazz.newInstance();
       }
       catch (Exception ex) {
         LeeLog.e(ex);
         LeeLog.e("找不到指定的类");
//         logger.info("�Ҳ���ָ������");
       }
     }
     return null;
   }
 }

