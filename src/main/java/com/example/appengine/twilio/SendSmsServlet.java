/**
 * Copyright 2015 Google Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class SendSmsServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException,
            ServletException {
        final String twilioAccountSid = System.getenv("TWILIO_ACCOUNT_SID");
        final String twilioAuthToken = System.getenv("TWILIO_AUTH_TOKEN");
        final String twilioNumber = System.getenv("TWILIO_NUMBER");
        final String toNumber = req.getParameter("to");
        if (toNumber == null) {
            resp.getWriter()
                    .print("Please provide the number to message in the \"to\" query string parameter.");
            return;
        }


        Twilio.init(twilioAccountSid, twilioAuthToken);

        Message message = Message.creator(new PhoneNumber(toNumber),
                new PhoneNumber(twilioNumber), "Hello from Twilio!").create();

        System.out.println(message.getSid());
    }
}
