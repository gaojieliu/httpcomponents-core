/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.hc.core5.http.protocol;

import java.io.IOException;

import org.apache.hc.core5.annotation.Contract;
import org.apache.hc.core5.annotation.ThreadingBehavior;
import org.apache.hc.core5.http.EntityDetails;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpRequestInterceptor;
import org.apache.hc.core5.util.Args;

/**
 * RequestUserAgent is responsible for adding {@code User-Agent} header.
 * This interceptor is recommended for client side protocol processors.
 *
 * @since 4.0
 */
@Contract(threading = ThreadingBehavior.IMMUTABLE)
public class RequestUserAgent implements HttpRequestInterceptor {

    /**
     * Singleton instance.
     * @since 5.2
     */
    public static final HttpRequestInterceptor INSTANCE = new RequestUserAgent();

    private final String userAgent;

    public RequestUserAgent(final String userAgent) {
        super();
        this.userAgent = userAgent;
    }

    public RequestUserAgent() {
        this(null);
    }

    @Override
    public void process(final HttpRequest request, final EntityDetails entity, final HttpContext context)
        throws HttpException, IOException {
        Args.notNull(request, "HTTP request");
        if (!request.containsHeader(HttpHeaders.USER_AGENT) && this.userAgent != null) {
            request.addHeader(HttpHeaders.USER_AGENT, this.userAgent);
        }
    }

}
