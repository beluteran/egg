package servicios;

import entidades.Autor;
import entidades.Editorial;
import entidades.Libro;
import excepciones.MiExcepciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositorios.AutorRepository;
import repositorios.EditorialRepository;
import repositorios.LibroRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {
    // crear libro
    // editar libro
    @Autowired
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private EditorialRepository editorialRepositorio;
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepciones{
        validarLibro(isbn,titulo,idAutor,idEditorial,ejemplares);

        Libro libro = new Libro();
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros(){
        List<Libro> lista = new ArrayList<>();
        lista = libroRepositorio.findAll();
        return lista;
    }
    @Transactional
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiExcepciones{

        validarLibro(isbn,titulo,idAutor,idEditorial,ejemplares);
        Optional <Libro> respuesta = libroRepositorio.findById(isbn);
        Optional <Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);
        // devuelve false si no encuentra nada

        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        if(respuestaAutor.isPresent()){
            autor = respuestaAutor.get();
        }
        if(respuestaEditorial.isPresent()){
            editorial = respuestaEditorial.get();
        }
        if(respuesta.isPresent()){
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libroRepositorio.save(libro);
        }
    }
    public void validarLibro(Long isbn,String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiExcepciones{
        if(isbn == null){
            throw new MiExcepciones("El isbn no puede ser nulo");
        }
        if(titulo.isEmpty() || titulo == null){
            throw new MiExcepciones("El titulo no puede ser nulo o vacio");
        }
        if(ejemplares == null){
            throw new MiExcepciones("No puede tener ejempleares nulo");
        }
        if(idAutor.isEmpty() || idAutor == null){
            throw new MiExcepciones("El id del autor no puede ser nulo");
        }
        if(idEditorial.isEmpty() || idEditorial == null){
            throw new MiExcepciones("La editorial no puede ser nula o vacia");
        }
    }
}
