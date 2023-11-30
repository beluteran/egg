package controladores;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/autor") //localhost:8080/autor
public class AutorControlador {

    @GetMapping("/registrar")//llocalhost:8080/autor/registrar
    public String registrar() {
        return "index.html";
    }

}

