package controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") //configura cual es la url que va a escuchar a este controlador
public class PortalControlador {

    @GetMapping("/")
    public String index(){
        return "index.html";
    }
}
