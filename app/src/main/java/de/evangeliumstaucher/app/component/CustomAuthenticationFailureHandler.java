package de.evangeliumstaucher.app.component;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

import static de.evangeliumstaucher.app.component.SecurityChainConfig.LOGIN_FAIL_URL;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    Gson gson = new Gson();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // Step 3: Add the exception as a request attribute
        request.setAttribute("error", exception.getMessage());
        //request.getRequestDispatcher(LOGIN_FAIL_URL).forward(request, response);

        response.sendRedirect(LOGIN_FAIL_URL + "=" + URLEncoder.encode(exception.getMessage()));

    }
}