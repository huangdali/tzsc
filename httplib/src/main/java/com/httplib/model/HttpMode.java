package com.httplib.model;

import com.httplib.http.HttpConfiger;

/**
 * HTTP请求的模式。
 * <p>RELEASE表示正式环境（默认）</p>
 * <p>DEBUG表示调试模式,该模式下用户可通过{@link HttpConfiger#closeDebugMode()(HttpMode, String)}方法设置测试主机域名</p>
 *
 * @author HDL
 */

public enum HttpMode {
    /**
     * 调试模式
     */
    DEBUG,
    /**
     * 正式环境
     */
    RELEASE
}
