
import DAO.AgendamentoDAO;
import DAO.TarifaDAO;
import DAO.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import modelo.Agendamento;
import modelo.Tarifa;
import modelo.Usuario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author apolo
 */
public class teste {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException, ParseException {

//        try {
//            String data = "16/07/2008";
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Calendar cal = Calendar.getInstance();
//            cal.setTime(sdf.parse(data));
//            System.out.println(cal);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        AgendamentoDAO dao = new AgendamentoDAO();
        Agendamento busca = dao.getAgendamento(10);
        
        System.out.println(busca.getCdAgendamento());
        System.out.println(busca.getCdServico());
        System.out.println(busca.getCdTpVeiculo());
        System.out.println(busca.getCdUsuario());
        System.out.println(busca.getDataParaView());
        System.out.println(busca.getData());
        System.out.println(busca.getHorario());
        System.out.println(busca.getPreco());//null
        System.out.println(busca.getServico());//null
        System.out.println(busca.getStatus());
        System.out.println(busca.getTipoVeiculo());//null
        System.out.println(busca.getUsuario());//null
    }
}
