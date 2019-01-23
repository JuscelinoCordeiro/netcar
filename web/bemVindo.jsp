<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/estilo.css">
        <title>The Affable Bean</title>
    </head>
    <body>
        <div class="principal">
            <%-- comentário em JSP aqui: nossa primeira página JSP --%>
            <%
                String mensagem = "Bem vindo ao sistema de agendamento de NetCar!";
            %>
            <% out.println(mensagem); %><br />
            <%
                String desenvolvido = "Desenvolvido por Juscelino Cordeiro";
                String teste = "Teste de variável em java";
            %>

            <%= desenvolvido%><br />
            <br>
            <br>
            <%= teste%><br><br>
            <%
                out.println("Tudo foi executado!");
            %>
        </div>
    </body>
</html>
