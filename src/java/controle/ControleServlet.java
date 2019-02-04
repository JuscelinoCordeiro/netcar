/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import DAO.AgendamentoDAO;
import DAO.FaturamentoDAO;
import DAO.ServicoDAO;
import DAO.TarifaDAO;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Agendamento;
import modelo.Servico;
import modelo.Tarifa;
import modelo.Usuario;

/**
 *
 * @author apolo
 */
@WebServlet(name = "ControleServlet",
        loadOnStartup = 1,
        urlPatterns = {
            "/servico_listar", "/servico_editar", "/servico_excluir",
            "/agendamento_listar", "/agendamento_adicionar", "/agendamento_editar", "/agendamento_finalizar",
            "/agendamento_excluir", "/agendamento_todos", "/agendamento_pesquisar",
            "/usuario_listar", "/usuario_adicionar", "/usuario_editar", "/usuario_excluir",
            "/tarifa_listar", "/tarifa_editar",
            "/faturamento", "/faturamento_diario", "/faturamento_mensal",
            "/mensagem", "/mensagem_erro", "/ajuda", "/login"
        })

public class ControleServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();

// tratamento de requisicao de paginas
//exclusao de usuario
        if (userPath.equals("/usuario_excluir")) {
            try {
                UsuarioDAO dao = new UsuarioDAO();
                int cdUsuario = Integer.parseInt(request.getParameter("id"));
                dao.removeUsuario(cdUsuario);
                userPath = "/mensagem";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
//exclusao de servico
        } else if (userPath.equals("/servico_excluir")) {
            try {
                ServicoDAO dao = new ServicoDAO();
                int cdServico = Integer.parseInt(request.getParameter("cd_servico"));
                dao.desativaServico(cdServico);
                userPath = "/mensagem";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
//finalizacao de servico
        } else if (userPath.equals("/agendamento_finalizar")) {
            try {
                AgendamentoDAO dao = new AgendamentoDAO();
                int codigo = Integer.parseInt(request.getParameter("id"));
                dao.finalizaAgendamento(codigo);
                Agendamento ag = dao.getAgendamento(codigo);
                FaturamentoDAO daoFatura = new FaturamentoDAO();
                daoFatura.faturamento(ag);
                userPath = "/mensagem";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
//exclusao de agendamento
        } else if (userPath.equals("/agendamento_excluir")) {
            try {
                AgendamentoDAO dao = new AgendamentoDAO();
                int codigo = Integer.parseInt(request.getParameter("id"));
                dao.removeAgendamento(codigo);
                userPath = "/mensagem";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//montagem da url para redirecionamento
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        String mensagem = null;
        PrintWriter out = response.getWriter();
// adicionar usuario
        if (userPath.equals("/usuario_adicionar")) {
            try {
                String nome = request.getParameter("nome");
                String endereco = request.getParameter("endereco");
                String celular = request.getParameter("celular");
                String fixo = request.getParameter("fixo");
                int idt = Integer.parseInt(request.getParameter("idt"));

                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(nome);
                novoUsuario.setEndereco(endereco);
                novoUsuario.setCelular(celular);
                novoUsuario.setFixo(fixo);
                novoUsuario.setIdt(idt);

                UsuarioDAO dao;
                dao = new UsuarioDAO();
                dao.adicionaUsuario(novoUsuario);

                userPath = "/mensagem";
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControleServlet.class.getName()).log(Level.SEVERE, null, ex);
                userPath = "/mensagem_erro";
            }
//editar usuario
        } else if (userPath.equals("/usuario_editar")) {
            try {
                String nome = request.getParameter("nome");
                String endereco = request.getParameter("endereco");
                String celular = request.getParameter("celular");
                String fixo = request.getParameter("fixo");
                int idt = Integer.parseInt(request.getParameter("idt"));
                int cdUsuario = Integer.parseInt(request.getParameter("cd_usuario"));

                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(nome);
                novoUsuario.setEndereco(endereco);
                novoUsuario.setCelular(celular);
                novoUsuario.setFixo(fixo);
                novoUsuario.setIdt(idt);
                novoUsuario.setCdUsuario(cdUsuario);

                UsuarioDAO dao;
                dao = new UsuarioDAO();
                dao.alteraUsuario(novoUsuario);

                userPath = "/mensagem";
            } catch (NumberFormatException | ClassNotFoundException e) {
            }
//adicionar agendamento
        } else if (userPath.equals("/agendamento_adicionar")) {
            try {
                String cdUsuario = request.getParameter("cd_usuario");
                String cdTpVeiculo = request.getParameter("cd_tpveiculo");
                String placa = request.getParameter("placa");
                String cdServico = request.getParameter("cd_servico");
                String data = request.getParameter("data");
                String horario = request.getParameter("horario");

                Agendamento novo = new Agendamento();
                novo.setCdUsuario(Integer.parseInt(cdUsuario));
                novo.setCdTpVeiculo(Integer.parseInt(cdTpVeiculo));
                novo.setPlaca(placa);
                novo.setCdServico(Integer.parseInt(cdServico));

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar dataBD = Calendar.getInstance();
                    dataBD.setTime(sdf.parse(data));
                    novo.setData(dataBD);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
                Date dataTime = formatador.parse(horario);
                Time time = new Time(dataTime.getTime());
                novo.setHorario(time);

                AgendamentoDAO dao = new AgendamentoDAO();
                dao.agendar(novo);

                userPath = "/mensagem";
            } catch (Exception e) {
            }
//editar agendamento
        } else if (userPath.equals("/agendamento_editar")) {
            try {
                String cdUsuario = request.getParameter("cd_usuario");
                String cdTpVeiculo = request.getParameter("cd_tpveiculo");
                String placa = request.getParameter("placa");
                String cdServico = request.getParameter("cd_servico");
                String data = request.getParameter("data");
                String horario = request.getParameter("horario");

                Agendamento ag = new Agendamento();
                ag.setCdUsuario(Integer.parseInt(cdUsuario));
                ag.setCdTpVeiculo(Integer.parseInt(cdTpVeiculo));
                ag.setPlaca(placa);
                ag.setCdServico(Integer.parseInt(cdServico));

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar dataBD = Calendar.getInstance();
                    dataBD.setTime(sdf.parse(data));
                    ag.setData(dataBD);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
                Date dataTime = formatador.parse(horario);
                Time time = new Time(dataTime.getTime());
                ag.setHorario(time);

                AgendamentoDAO dao = new AgendamentoDAO();
                dao.alteraAgendamento(ag);

                userPath = "/mensagem";
            } catch (Exception e) {
            }
//editar servico
        } else if (userPath.equals("/servico_editar")) {
            try {
                int cdServico = Integer.parseInt(request.getParameter("cd_servico"));
                String servico = request.getParameter("servico");

                Servico sv = new Servico();
                sv.setCdServico(cdServico);
                sv.setServico(servico);

                ServicoDAO dao = new ServicoDAO();
                dao.alteraServico(sv);

                userPath = "/mensagem";
            } catch (Exception e) {
            }
            userPath = "/mensagem";
//editar tarifa
        } else if (userPath.equals("/tarifa_editar")) {
            try {
                int cdServico = Integer.parseInt(request.getParameter("cd_servico"));
                int cdTpVeiculo = Integer.parseInt(request.getParameter("cd_tpveiculo"));
                float preco = Float.parseFloat(request.getParameter("preco"));

                Tarifa tarifa = new Tarifa();
                tarifa.setCdServico(cdServico);
                tarifa.setCdTpVeiculo(cdTpVeiculo);
                tarifa.setPreco(preco);

                TarifaDAO dao = new TarifaDAO();
                dao.alteraTarifa(tarifa);

                userPath = "/mensagem";
            } catch (Exception e) {
            }
        } else if (userPath.equals("/login")) {
            try {
                String senha = request.getParameter("senha");
                int idt = Integer.parseInt(request.getParameter("idt"));

                UsuarioDAO dao = new UsuarioDAO();

                if (dao.existeUsuario(idt, senha)) {
                    userPath = "/index";
                } else {
                    userPath = "/erro";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //montagem da url para redirecionamento
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
