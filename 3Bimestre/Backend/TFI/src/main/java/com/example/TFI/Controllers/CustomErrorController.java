package com.example.TFI.Controllers;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {
        String errorTitle = "";
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        switch (httpErrorCode) {
            case 400: {
                errorTitle = "Error 400: Solicitud incorrecta";
                errorMsg = "Error 400: Solicitud incorrecta.";
                break;
            }
            case 401: {
                errorTitle = "Error 401: No autorizado";
                errorMsg = "Error 401: Debes iniciar sesión para acceder a esta página.";
                break;
            }
            case 403: {
                errorTitle = "Error 403: Prohibido";
                errorMsg = "Error 403: No tienes los permisos necesarios para acceder a esta página.";
                break;
            }
            case 404: {
                errorTitle = "Error 404: No encontrado";
                errorMsg = "Error 404: No encontramos lo que estás buscando.";
                break;
            }
            case 500: {
                errorTitle = "Error 500: Error interno del servidor";
                errorMsg = "Error 500: No te preocupes, es nuestra culpa.";
                break;
            }
        }
        model.addAttribute("errorTitle", errorTitle);
        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (int) httpRequest.getAttribute("javax.servlet.error.status_code");
    }
}