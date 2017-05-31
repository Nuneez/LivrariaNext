package br.senac.tadsb.pi3.livrarianext.servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.senac.tadsb.pi3.livrarianext.database.*;
import br.senac.tadsb.pi3.livrarianext.exceptions.LojaException;
import br.senac.tadsb.pi3.livrarianext.models.Loja;
import br.senac.tadsb.pi3.livrarianext.servicos.ServicoLoja;
import br.senac.tadsb.pi3.livrarianext.validar.CaracteresEspeciais;
import br.senac.tadsb.pi3.livrarianext.validar.Cnpj;
import br.senac.tadsb.pi3.livrarianext.validar.Email;
import br.senac.tadsb.pi3.livrarianext.validar.Telefone;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nunez
 */
public class ManterLojas extends HttpServlet {

    ServicoLoja servico;

    public ManterLojas() {
        try {
            ConnectionUtils util = new ConnectionUtils();
            servico = new ServicoLoja(new DaoLoja(util));
        } catch (LojaException ux) {
            System.out.println(ux.getMessage());
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

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
        try {
            String id = request.getParameter("id");
            Loja dominio = (id != null && !id.isEmpty()) ? servico.obterLojaPorId(Integer.parseInt(id)) : new Loja();
            request.setAttribute("loja", dominio);
        } catch (LojaException ce) {
            request.setAttribute("erro", ce.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("Loja.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (IOException ex) {

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

        String mensagemDeErro = null;

        //Obtendo parametros
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String filial = request.getParameter("filial");
        String razaoSocial = request.getParameter("razaosocial");
        String ativo = request.getParameter("ativo");
        String cnpj = request.getParameter("cnpj");
        String inscricaoEstadual = request.getParameter("inscricaoestadual");
        String endereco = request.getParameter("endereco");
        String numero = request.getParameter("numero");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");

        try {
            if (!Cnpj.validar(cnpj)) {
                mensagemDeErro += " \n CNPJ inválido, digite novamente !";
            }

            if (!Email.validar(email)) {
                mensagemDeErro += " \n E-mail inválido, digite novamente !";
            }

            if (!Telefone.validar(telefone)) {
                mensagemDeErro += " \n Telefone inválido, digite novamente !";
            }

            if (nome.trim().isEmpty()) {
                mensagemDeErro += " \n Campo NOME obrigatorio !";
            }

            if (cnpj.trim().isEmpty()) {
                mensagemDeErro += " \n Campo CNPJ obrigatório !";
            }

            if (validarCnpjExistente(cnpj, Integer.parseInt(id))) {
                mensagemDeErro += " \n CNPJ pertencente a outro cliente cadastrado!";
            }

            request.setAttribute("erro", mensagemDeErro);

            if (mensagemDeErro != null && !mensagemDeErro.isEmpty()) {
                dispatchFailedPost(new Loja(nome, filial, razaoSocial, cnpj, inscricaoEstadual, endereco, numero, cidade, estado, email, telefone), request, response);
                return;
            }

            if (id.isEmpty() || id.equals("0")) {
                servico.incluir( nome, null, razaoSocial, cnpj, inscricaoEstadual, endereco, numero, cidade, estado, email, telefone);
            } else {
                servico.alterar(Integer.parseInt(id), nome, null, razaoSocial, cnpj, inscricaoEstadual, endereco, numero, cidade, estado, email, telefone);
            }

            response.sendRedirect("ListarLojas");
        } catch (LojaException ue) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ue);
            request.setAttribute("erro", mensagemDeErro);
            dispatchFailedPost(new Loja(nome, filial, razaoSocial, cnpj, inscricaoEstadual, endereco, numero, cidade, estado, email, telefone), request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManterUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("erro", "Não foi possível completar a operação.");
            dispatchFailedPost(new Loja(nome, filial, razaoSocial, cnpj, inscricaoEstadual, endereco, numero, cidade, estado, email, telefone), request, response);
        }
    }

    private void dispatchFailedPost(Loja loja, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("loja", loja);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Loja.jsp");
        dispatcher.forward(request, response);
    }

    private boolean validarCnpjExistente(String cnpj, int id) throws LojaException {
        Loja loja = servico.obterLojaPorId(id);

        if (loja == null) {
            return false;
        }
        return loja.getId() != id;
    }
}
