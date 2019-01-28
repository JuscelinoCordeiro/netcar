package controle;

import DAO.UsuarioDAO;
import javax.servlet.http.HttpSession;
import modelo.Usuario;

/**
 *
 * @author hefesto
 */
/*@Controller*/
public class LoginController {

//    @RequestMapping("loginForm")
    public String loginForm() {
        return "formulario-login";
    }

//    @RequestMapping("efetuaLogin")
    public String efetuaLogin(Usuario usuario, HttpSession session) throws ClassNotFoundException {
        if (new UsuarioDAO().existeUsuario(usuario)) {
            session.setAttribute("usuarioLogado", usuario);
            return "index";
        }
        return "redirect:login";
    }

}
