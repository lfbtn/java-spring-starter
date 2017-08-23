package br.lb.admin.entity;

public final class AppConfiguration {

    private static final int appId = 1;
    private static final boolean appHasContabil = false;
    private static final boolean appHasCompras = false;
    private static final boolean appHasEric = true;
    private static final String appName = "Bots";
    private static final String appNickName = "Bots";
    private static final String[] MODULES = { "USUARIOS", "HOLERITES", "TOTALIP", "MOE" };

    private static final String appIntro = "Aqui, dependendo do seu tipo de acesso você tem acesso a diversas funcionalidades.";
    private static final String appIntroTitle1 = "Usuários";
    private static final String appIntroDesc1 = "Aqui você cadastra usuários, concede permissões e gerencia os clientes.";
    private static final String appIntroTitle2 = "Monitoramento da Operação";
    private static final String appIntroDesc2 = "Monitoramento das principais métricas de produção do sistema. ";
    private static final String appIntroTitle3 = "Consulta Clientes";
    private static final String appIntroDesc3 = "Acesso aos clientes de todas as modalidades simultâneamente.";
    private static final String appIntroTitle4 = "Comming Soon..";
    private static final String appIntroDesc4 = "Em breve outras novidades.";

    private static final String appEmail = "naoresponda@";
    private static final String appMailHostServer = "192.168.0.3";
    private static final String appMailHostPort = "25";
    private static final String appMailPass = "1CQsTQtX";

    public static String getAppName() {
	return appName;
    }

    public static String getAppname() {
	return appName;
    }

    public static String getAppnickname() {
	return appNickName;
    }

    public static String[] getModules() {
	return MODULES;
    }

    public static String getAppIntro() {
	return appIntro;
    }

    public static String getAppintro() {
	return appIntro;
    }

    public static String getAppemail() {
	return appEmail;
    }

    public static String getAppmailhostserver() {
	return appMailHostServer;
    }

    public static String getAppmailhostport() {
	return appMailHostPort;
    }

    public static String getAppmailpass() {
	return appMailPass;
    }

    public static int getAppid() {
        return appId;
    }

    public static String getAppintrotitle1() {
        return appIntroTitle1;
    }

    public static String getAppintrodesc1() {
        return appIntroDesc1;
    }

    public static String getAppintrotitle2() {
        return appIntroTitle2;
    }

    public static String getAppintrodesc2() {
        return appIntroDesc2;
    }

    public static String getAppintrotitle3() {
        return appIntroTitle3;
    }

    public static String getAppintrodesc3() {
        return appIntroDesc3;
    }

    public static String getAppintrotitle4() {
        return appIntroTitle4;
    }

    public static String getAppintrodesc4() {
        return appIntroDesc4;
    }

}
