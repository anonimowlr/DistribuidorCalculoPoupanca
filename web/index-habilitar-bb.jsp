

<%@page import="br.com.calculopoupanca.model.dao.FuncionarioDAO"%>
<%
    boolean precisaAutenticar = false;
    if (session.getAttribute("usuarioLogado") == null) {
        if (request.getParameter("ibm-nativeid") == null) {
            precisaAutenticar = true;
        } else {
            //session.setAttribute("usuarioLogado", new FuncionarioDAO().getFuncionario(request.getParameter("cd-idgl-usu")));
            session.setAttribute("usuarioLogado", new FuncionarioDAO().getFuncionario(request.getParameter("ibm-nativeid"),request.getParameter("cd-eqp"),request.getParameter("nm-idgl")));
                
            if (session.getAttribute("usuarioLogado") != null) {
                String uri = request.getScheme() + "://"
                        + request.getServerName()
                        + ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort())
                        + request.getRequestURI()
                        + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
                response.sendRedirect("https://cenopservicoscwb.intranet.bb.com.br/_tools/loginPortal.php?urlRetorno=" + uri + "home.jsf");

            }
        }
    } else {
        String uri = request.getScheme() + "://"
                + request.getServerName()
                + ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort())
                + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        response.sendRedirect("https://cenopservicoscwb.intranet.bb.com.br/_tools/loginPortal.php?urlRetorno=" + uri + "home.jsf");

    }

    if (precisaAutenticar) {
        String uri = request.getScheme() + "://"
                + request.getServerName()
                + ("http".equals(request.getScheme()) && request.getServerPort() == 80 || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? "" : ":" + request.getServerPort())
                + request.getRequestURI()
                + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        response.sendRedirect("https://cenopservicoscwb.intranet.bb.com.br/_tools/loginPortal.php?urlRetorno=" + uri);
    }


%>



<html>

    <p>Carregando pagina.....</p>   
</html>





