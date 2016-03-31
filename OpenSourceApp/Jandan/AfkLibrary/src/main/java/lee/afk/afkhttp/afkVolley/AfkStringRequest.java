/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lee.afk.afkhttp.afkVolley;

import lee.afk.afkhttp.volley.NetworkResponse;
import lee.afk.afkhttp.volley.Request;
import lee.afk.afkhttp.volley.Response;
import lee.afk.afkhttp.volley.Response.ErrorListener;
import lee.afk.afkhttp.volley.Response.Listener;
import lee.afk.afkhttp.volley.toolbox.HttpHeaderParser;

public class AfkStringRequest extends Request<String> {
    private final Listener<String> mListener;

    public AfkStringRequest(int method, String url, Listener<String> listener,
                            ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }

    public AfkStringRequest(String url, Listener<String> listener, ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        parsed = new String(response.data);
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

}
