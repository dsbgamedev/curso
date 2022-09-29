package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.map.HashedMap;

import jakarta.servlet.ServletContext;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

@SuppressWarnings({"unchecked", "rawtypes"})
public class ReportUtil implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
public byte[] geraRelatorioExcel( List listadado, String nomeRelatorio,HashMap<String,Object> params, ServletContext servletContext) throws Exception{
		
		/*Cria a lista de dados do nosso SQL da consulta feita*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listadado);
		
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper,params, jrbcds);
		
		JRExporter exporter = new JRXlsExporter();/*Exel*/
		
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);                  
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		
		exporter.exportReport();
		
		return baos.toByteArray();
	}

	
public byte[] geraRelatorioPDF( List listadado, String nomeRelatorio,HashMap<String,Object> params, ServletContext servletContext) throws Exception{
		
		/*Cria a lista de dados do nosso SQL da consulta feita*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listadado);
		
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper,params, jrbcds);
		
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}

	public byte[] geraRelatorioPDF( List listadado, String nomeRelatorio, ServletContext servletContext) throws Exception{
		
		/*Cria a lista de dados do nosso SQL da consulta feita*/
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listadado);
		
		String caminhoJasper = servletContext.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashedMap(), jrbcds);
		
				
		return JasperExportManager.exportReportToPdf(impressoraJasper);
	}

}
