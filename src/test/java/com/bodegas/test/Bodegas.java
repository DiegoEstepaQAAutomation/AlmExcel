

package com.bodegas.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demoautomatizacion.BaseTest;
import com.demoautomatizacion.Pages.BasePage;

import com.demoautomatizacion.test.utils.Listeners.TestListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import utilities.GenerarReportePdf;
import utilities.MyScreenRecorder;

@Listeners({ TestListener.class })
@Epic("Bodegas")
@Feature("Bodegas Tests")

public class Bodegas extends BaseTest {
	public Properties fileprops = new Properties();

	//PROPIEDADES UTILIZADAS
	public Properties getProperties() throws Exception {
		fileprops.load(new FileInputStream(new File("src/test/resources/test.properties").getAbsolutePath()));
		return fileprops;
	}

	//Evidencia
	/** The recording. */
	//INSTANCIA DE MY SCREEN RECORDER(GRABACION DE PANTALLA)
	MyScreenRecorder recording;
	//METODO PARA LOGUEARSE AL PORTAL DE ALMAVIVA
	public void login2(String nameTest, String usuario, String contrasena,String Evidencia) throws Exception 
	{

		//INSTANCIA DEL METODO DE GENERAR EL REPORTE PDF
		GenerarReportePdf.setRutaImagen(getProperties().getProperty("routeImageReport"));
		//INSTANCIA DE LA RUTA DONDE GUARDAMOS EL PDF
		File folderPath = BasePage.createFolder(getProperties().getProperty("nameFolderG"),
				getProperties().getProperty("path"),getProperties().getProperty("Evidencia"));

		GenerarReportePdf.createTemplate(folderPath, nameTest, getProperties().getProperty("analista"),
				getProperties().getProperty("urlPrivada"),getProperties().getProperty("Evidencia"));

		GenerarReportePdf.setImgContador(0);

		//LLAMADO DE CREDENCIALES Y LA RUTA URL DEL PORTAL DE ALMAVIVA
		home.irPortal(getProperties().getProperty("urlPrivada"));
		login.privacidadIp();
		home.irPortal(getProperties().getProperty("url"));
		login.privacidadIp();
		home.irPortal(getProperties().getProperty("urlPrivada"));
		login.ingresarCredenciales(getProperties().getProperty("usuario2"), getProperties().getProperty("password"),
				folderPath,getProperties().getProperty("Evidencia"));
	}

	//PASO A PASO DE CREACION DE UNA BODEGA
	@SuppressWarnings("static-access")
	@Test(priority = 0, description = "Validar que Permita la creaci??n de la informaci??n de bodegas pertenecientes a Almaviva y/o particulares autorizadas")
	@Severity(SeverityLevel.NORMAL)
	@Description("Validar que Permita la creaci??n de la informaci??n de bodegas pertenecientes a Almaviva y/o particulares autorizadas")
	@Story("Validar que Permita la creaci??n de la informaci??n de bodegas pertenecientes a Almaviva y/o particulares autorizadas")
	public void AlmavivaBodegas1() throws Exception {
		//VALIDAR ESTOS PASOS EN EXPEDIR TEST EN EL METODO CREAR EXPEDIR
		File folderPath = BasePage.createFolder(getProperties().getProperty("nameFolderBodega"),
				getProperties().getProperty("path"),getProperties().getProperty("Evidencia"));
		
		recording.startRecording("grabando", folderPath);

		login2(getProperties().getProperty("nameTestBodega"), getProperties().getProperty("usuario"),
				getProperties().getProperty("password"),getProperties().getProperty("Evidencia"));

		home.modulo(folderPath, getProperties().getProperty("ModuloP"), getProperties().getProperty("SubModuloB"),getProperties().getProperty("Evidencia"));

		//EJECUCION DE LOS CASOS DE BODEGA
		bodega.Parametrizacion(folderPath,getProperties().getProperty("Evidencia"));
		login.cerrarSesion(folderPath,getProperties().getProperty("Evidencia"));
		
		recording.stopRecording();

		// FillFormulary(folderPath, "CodigoBodeg", "oficinaBodeg", "Propietario",
		// "Ubicacion", "NumeroTelefonico");

		GenerarReportePdf.closeTemplate("",getProperties().getProperty("Evidencia"));
	}
	
	
	
	//PASO A PASO DE CREACION DE UNA BODEGA
		@SuppressWarnings("static-access")
		@Test(priority = 0, description = "Validar que Permita la creaci??n de la informaci??n de bodegas pertenecientes a Almaviva y/o particulares autorizadas")
		@Severity(SeverityLevel.NORMAL)
		@Description("Validar que Permita la creaci??n de la informaci??n de bodegas pertenecientes a Almaviva y/o particulares autorizadas")
		@Story("Validar que Permita la creaci??n de la informaci??n de bodegas pertenecientes a Almaviva y/o particulares autorizadas")
		public void AlmavivaBodegasScav() throws Exception {
			
			
			//VALIDAR ESTOS PASOS EN EXPEDIR TEST EN EL METODO CREAR EXPEDIR
			File folderPath = BasePage.createFolder(getProperties().getProperty("nameFolderBodega"),
					getProperties().getProperty("path"),getProperties().getProperty("Evidencia"));
			
			
			recording.startRecording("grabando", folderPath);
			
			login2(getProperties().getProperty("nameTestBodega"), getProperties().getProperty("usuario"),
					getProperties().getProperty("password"),getProperties().getProperty("Evidencia"));

			home.modulo(folderPath, getProperties().getProperty("ModuloP"), getProperties().getProperty("SubModuloB"),getProperties().getProperty("Evidencia"));

			//EJECUCION DE LOS CASOS DE BODEGA
			bodega.ScavValidacion_571747(folderPath, getProperties().getProperty("ValorFecha2"), getProperties().getProperty("Evidencia"));
			
			login.cerrarSesion(folderPath,getProperties().getProperty("Evidencia"));
			
			recording.stopRecording();
			
			// FillFormulary(folderPath, "CodigoBodeg", "oficinaBodeg", "Propietario",
			// "Ubicacion", "NumeroTelefonico");

			GenerarReportePdf.closeTemplate("",getProperties().getProperty("Evidencia"));
		}

	
	
	
	
	
}
