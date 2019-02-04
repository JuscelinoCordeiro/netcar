<div class="view-login">
    <div id="formLogin">
        <form name="form-login" action="/netcar/login" method="post">
            <fieldset>
                <legend>NetCar - Login</legend>
                <label>
                    Identidade
                </label>
                <input class="input-block-level" type="text" name="idt" placeholder="Digite sua identidade" required />

                <label>Senha</label>
                <input class="input-block-level" type="password" name="senha" placeholder="Digite sua senha" required />

                <input type="hidden" name="acao" value="logar"/>
                <input type="submit" value="Entrar" class="btn btn-primary btn-login"/>

            </fieldset>
        </form>
    </div><!--fim da div formlogin-->
</div>


