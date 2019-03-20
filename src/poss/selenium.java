package poss;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class selenium {

	public static void main(String[] args) throws InterruptedException {
		  
        String UrlAmbiente = JOptionPane.showInputDialog(null,"Url Ambiente. ","http://190.57.147.130:2080/GrupombTest/");  // el icono sera un iterrogante
        String caja = JOptionPane.showInputDialog(null,"Caja a desvincular.","M16_C3");  // el icono sera un iterrogante
        String UrlSideposs = JOptionPane.showInputDialog(null,"Url Sideposs.","http://190.57.147.130:2080/GrupombTest/web/org.openbravo.retail.posterminal/?terminal=M16_C3#");  // el icono sera un iterrogante
		
        int numPruebasTotal = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de pruebas totales","1"));

		int numPedidos = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de pedidos por cliente","1"));
		
		if(UrlAmbiente == null || caja == null || UrlSideposs ==null || numPruebasTotal <=0 || numPedidos <=0) {
	        JOptionPane.showMessageDialog(null, "Error en los campos solicitados.");

		}
	//	desvincular(UrlAmbiente, caja);
		
		//==================ingresar y relizar pruimer login =====================================
		    WebDriver driverS = new ChromeDriver();
			driverS.get(UrlSideposs);
			Thread.sleep(1500);
		//	driverS.manage().window().maximize();
			Thread.sleep(4500);
			System.out.println("***** *****  Login SIDEPOSS ***** ******");
	   
	      
			WebElement key1 = driverS.findElement(By.id("terminal_confirmationContainer_modalSelectTerminal_bodyButtons_terminalKeyIdentifier"));
			  key1.clear();
			  key1.sendKeys(caja);
			  	//USUARIO
			  WebElement usser = driverS.findElement(By.id("terminal_confirmationContainer_modalSelectTerminal_bodyButtons_username"));
			    usser.clear();
			    usser.sendKeys("SIDESOFT");
			    //PASSWORD
			   WebElement password1 = driverS.findElement(By.id("terminal_confirmationContainer_modalSelectTerminal_bodyButtons_password"));
			   password1.clear();
			   password1.sendKeys("SSECUADOR");
			   password1.sendKeys(Keys.ENTER);
				    
				    
				System.out.println("***** LogIn completado ******");
	                               
				// INGRESADNDOO TIEMPO AUTOMATICO NECESARIO HASTA CARGAR HTML
			    Thread.sleep(TimeUnit.SECONDS.toMillis(10)); 
		
		
		//===============================================================================================================

	  //***************************CLIENTES LOGIN*************************************
			System.out.println("***** Seleccion del cliente ******");
		
			for(int i=0; i < numPruebasTotal; i++) {
			System.out.println("Numero de prueba ejecutada : " + i);
			Random random = new Random();
			int lowerBound = 1;
			int upperBound = 3;
			int randomNumber = random.nextInt(upperBound - lowerBound) + lowerBound;
			
			Thread.sleep(2000);
		   // WebElement firstCL = driverS.findElement(By.id("terminal_containerWindow_login_loginUserContainer_userButton"+randomNumber));
		    WebElement firstCL = driverS.findElement(By.id("terminal_containerWindow_login_loginUserContainer_userButton2"));
		    firstCL.click(); 
		    	
		    
		    WebElement pass = driverS.findElement(By.id("terminal_containerWindow_login_password"));
		    pass.clear();
		    pass.sendKeys("1234");
		    pass.sendKeys(Keys.ENTER);

   	    	Thread.sleep(50000); //fijo
   	    	
   	    	
   	    	// ========================2) EMPESAR A BUSCAR PRODUCTOS ===================================
   	    		//BUCLE NUMERO DE PEDIDOS A REALIZAR POR EL CLIENTE
   	    	for(int ABC=0; ABC < numPedidos ; ABC++) {
   	    		System.out.println("Numero de pedidos realizados por cliente: " +ABC );
   	    		Thread.sleep(2000);
   	    		//ESTAS CAJAS TIENEN DIFERENTES LISTADOS FORMA DE PAGO****************************************************
   	    		if(caja.startsWith("M")) {
   	    			

   	    			//______________________________________________
   	    			
					//_____________________-----------PRIMER PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("RAPI POLLO PLANCHA + LIMONADA 12ONZ");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCount = 0;
					iCount = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCount; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicar = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicar.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					//LISTADO DE PRODUCTOS =====================================
					
					//_____________________-----------SEGUNDO PRODUCTO___________---------------______________------

   	    /*			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("CAMARON APANADO");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountB = 0;
					iCountB = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountB; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarB = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarB.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					
					//_____________________-----------TERCER PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("COMBO MAYLON");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountC = 0;
					iCountC = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountC; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarC = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarC.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					
					//_____________________-----------CUARTO PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("RAPI SALTARIN LOMO + GASEOSA 12 OZ");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountD = 0;
					iCountD = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountD; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarD = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarD.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					
					//_____________________-----------QUINTO PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("COMBO MAYLON");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountE = 0;
					iCountE = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountE; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarE = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarE.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
				*/	
					
					//==========================================================
					
					
					// ++++++  ASIGNAR CONSUMIDOR FINAL ++++++++ 
					Thread.sleep(3500); 
					WebElement asgConsumidor = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_leftPanel_receiptview_receiptheader_receiptButtons_bpbutton\"]"));
					asgConsumidor.click();
														
					//------------------ BUSCAR CONSUMIDOR FINAL -------------------------
					Thread.sleep(1000); 
					WebElement searchCons = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_modalcustomer_body_listBpsSelector_stBPAssignToReceipt_theader_modalBpSelectorScrollableHeader_filterSelector_entityFilterText\"]	"));
					searchCons.clear();//*[@id="isc_60"]
					
					// ----------- SELECIONAR CONSUMIDOR FINAL ---------------------------- 
			        String[] arr={ 
			        		 	"GANAN YANDUN LUIS EDUARDO" , 
			        			"TATA SOLUTIONS",
			        			"ALEJANDRA CRIOLLO"
			        				}; // CONSUMIDOR FINAL
			        
			        Random r=new Random();
			        int randomString=r.nextInt(arr.length);
			   
			        String nombre = arr[randomString];
					
					searchCons.sendKeys(nombre); //BUSCADOR POR TERMINO
					System.out.println("Consumidor final: " +nombre);
					Thread.sleep(5000); ////////// 
			        //click en el selecionado consumidor final
					WebElement seleccCon = driverS.findElement(By.xpath("//span[text()='"+nombre+"']"));
					Thread.sleep(6000); 
					seleccCon.click();
					// ------------FIN BUSCAR Y ASGINAR CONSUMIOR FINAL
					
					// ====== LISTO PARA SELECIONAR FORMA DE PAGO =======
					Thread.sleep(4500); 
					WebElement btTotal = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_leftToolbar_leftToolbar_toolbar_button3_theButton_btnTotalToPay\"]"));
	    	    	Thread.sleep(2500); 
	    	    	btTotal.click();
	    	    	Thread.sleep(4000);
	    	    	
	    	    	//BUSCAR LOS BOTONES EN LA LISTA 
	    	    		Boolean btA = driverS.findElements(By.xpath("//button[text()='Efectivo']")).size()>0;
				        Boolean btB = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
			        	Boolean btC = driverS.findElements(By.xpath("//button[text()='Ret. Iva ']")).size()>0;
			        	Boolean btD = driverS.findElements(By.xpath("//button[text()='Ret. renta ']")).size()>0;
	    	    	//los encuentra y agregelos al array
	    	    	
	    	    	ArrayList<String> arreglo = new ArrayList<String>();
	    	    			if(btA) {
	    	    		arreglo.add("//button[text()='Efectivo']");
	    	    	}
	    	    	/*			if(btC) {
		    	    		arreglo.add("//button[text()='Ret. Iva ']");
		    	    	}
	    	    		
	    	    	if(btB) {
	    	    		arreglo.add("//button[text()='Convenios']");
	    	    	}
	    	    		
	    	    	
	    	    	
	    	    	if(btD) {
	    	    		arreglo.add("//button[text()='Ret. renta ']");
	    	    	}
	    	    	*/
	    	    	System.out.println(" FORMAS DE PAGO: ");
	    			System.out.println(arreglo);
	    	    	   	
	    	    	// CONSUMIDOR FINAL 
			        Random bts=new Random();
			        //int randomBts=bts.nextInt(arreglo.length);
			        int randomBts=bts.nextInt(arreglo.size());
			        //System.out.println(arr[randomString]);
			        String etiqueta = arreglo.get(randomBts);
			        Thread.sleep(1500);
			      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
			      //button[text()='Efectivo']
			        WebElement formPago = driverS.findElement(By.xpath(etiqueta));
			        Thread.sleep(1500);
			
		        	formPago.click();
		        	
					Thread.sleep(2000); 
					
					if(formPago.isDisplayed()) {
						//-----------------------------------------------------------------------
						 if(etiqueta.endsWith("'Efectivo']")) {
					        	System.out.println("Pago realizado en :Efectivo");
					        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
						        Thread.sleep(1500); 
						        
						        Boolean ErrEfecConv = driverS.findElements(By.xpath("//div[text()='Error en Método de Pago']")).size()>0;
					       
						        if(ErrEfecConv){
						        	//CAMBIAR FORMA DE PAGO
							        Thread.sleep(1500); 
						        	driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnACEPTAR\"]")).click();
						        	System.out.println("Cambio de pago efectivo a convenio. 'Tipo de consumidor final'");
						        	Thread.sleep(1500); 
						        	//validar si existe el bt en la lista
						        	//valida si existe el bt en la lista
						        	
						        	 Boolean btExistC = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
						        	 WebElement btelemnt= driverS.findElement(By.xpath("//button[text()='Convenios']"));
						        	
						        	 if(btExistC && btelemnt.isDisplayed()) {
						        		 System.out.println("EXISTE");
						        		 //existe en la lista el BT
						        		 Thread.sleep(1500);
						        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
					     	   		     Thread.sleep(1500); 
								       	System.out.println("Pago realizado en :Convenio");
						              	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
							        	
						              //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
						              	
						              	Thread.sleep(1500);						        
						              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
						              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	int numC = ABC+1;
						              	
						              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	
						       
						       
						              	
						              	ArrayList<String> btsExist = new ArrayList<String>();
					    	    		if(bt0) {
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
					    	    		
					    	    		}
					    	    		if(bt2) {
					    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
					    	    			

					    	    		}
					    	    	System.out.println(" FORMAS DE PAGO: ");
					    			System.out.println(btsExist);
					    	    	   	
					    	    	// CONSUMIDOR FINAL 
							        Random num=new Random();
							        //int randomBts=bts.nextInt(arreglo.length);
							        int randomBtsE=num.nextInt(btsExist.size());
							        //System.out.println(arr[randomString]);
							        String button = btsExist.get(randomBtsE);
							        Thread.sleep(1500);
							      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
							      //button[text()='Efectivo']
							        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
							        Thread.sleep(1500);
							        btACEPTAR.click();  
							        Thread.sleep(1500);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");
						        	Thread.sleep(1000);
						              	
						 //=====
						              	
							        	
						        	 
						        	 }else {
						        		 System.out.println("kcon");
						        		 //no existe en la lista el BT   MAS....
						        		 Thread.sleep(1000);
						        		 driverS.findElement(By.xpath("//button[text()='Más...']")).click();
						        		 Thread.sleep(1500);
						        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
					     	   		     Thread.sleep(1500); 
								       	System.out.println("Pago realizado en :Convenio");
						              	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
						              //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
						              	
						              	Thread.sleep(1500);						        
						              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
						              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	int numC = ABC+1;
						              	System.out.println(numC);
						              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	
						       
						       
						              	
						              	ArrayList<String> btsExist = new ArrayList<String>();
					    	    		if(bt0) {
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
					    	    			System.out.println(bt0);
					    	    		}
					    	    		if(bt2) {
					    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
					    	    			System.out.println(bt2);

					    	    		}
					    	    	System.out.println(" FORMAS DE PAGO: ");
					    			System.out.println(btsExist);
					    	    	   	
					    	    	// CONSUMIDOR FINAL 
							        Random num=new Random();
							        //int randomBts=bts.nextInt(arreglo.length);
							        int randomBtsE=num.nextInt(btsExist.size());
							        //System.out.println(arr[randomString]);
							        String button = btsExist.get(randomBtsE);
							        Thread.sleep(1500);
							      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
							      //button[text()='Efectivo']
							        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
							        Thread.sleep(1500);
							        btACEPTAR.click();  
							        Thread.sleep(1500);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");
						        	Thread.sleep(1000);
						              	
						 //====
						        	 }
								       					       
						        	
						        }
						        else {
						        	//PROCESO NORMAL
						        	Thread.sleep(1000);
						    		 driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]")).click();
								     Thread.sleep(1500); 

					    	  		 WebElement btHecho = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]"));
					    	 		 btHecho.click();
							        	System.out.println("____________ Pedido realizado __________");

					    	 		 Thread.sleep(1000);
						        	// driverS.close();
						        }

				    	    					    	
					        }
							//-----------------------------------------------------------------------
						 
						 //------ convenio ---------
						 //existe convenio en la lista verifique
						 
						    if(etiqueta.endsWith("Convenios']")) {
					        	System.out.println("Pago realizado en :Convenio");
					        	Thread.sleep(1500);
					        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
					        	Thread.sleep(1500);
					        	driverS.findElement(By.xpath("//button[text()='ACEPTAR']")).click();
					        	Thread.sleep(1500);
//TALVEZ UN ERROR AKI 
//validar q el bt existe 					        	
					        	
					        	Boolean noConvenio = driverS.findElements(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnOk\"]")).size()>0;
					        	if(noConvenio) {
					        		System.out.println("EL cliente no es de tipo convenio, el pago se realizara por efectivo");
						        	Thread.sleep(1500);
					        		WebElement bt = driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnOk\"]"));
					        		bt.click();
					        		
							        	Thread.sleep(1500);
							        	driverS.findElement(By.id("terminal_containerWindow_pointOfSale_multiColumn_rightPanel_keyboard_toolbarcontainer_toolbarPayment_btnSide_OBPOS_payment.cash_button")).click();
							        	Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
								        Thread.sleep(1500); 

						    	    	
						    	 		 driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]")).click();
									     Thread.sleep(1500); 

						    	  		 WebElement btHecho = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]"));
						    	 		 btHecho.click();
						    	 		 Thread.sleep(1000);
							        //	 driverS.close();
								         System.out.println("____________ Pedido realizado __________");

						    	  		 
					        		
					        	}else { ///AKIII ULTIMA MODIFICACION
					        		Thread.sleep(1500);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");
						        	Thread.sleep(1000);
						        //	driverS.close();
					        	}


					        	
					        }
						    
						    if(etiqueta.endsWith("Ret. Iva ']")) {
					        	System.out.println("Pago realizado en :retencionIva");
					        	Thread.sleep(2000);
					        	
					        	//ERROR TIPO CONVENIO
					        	 Boolean ErrEfecConv = driverS.findElements(By.xpath("//div[text()='Error en Método de Pago']")).size()>0;
							        
							        if(ErrEfecConv){
								        Thread.sleep(1500); 
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnACEPTAR\"]")).click();
							        	System.out.println("Cambio de pago ret. iva a convenio. 'Tipo de consumidor final'");
							        	Thread.sleep(1500); 
							        	//valida si existe el bt en la lista
							        	
							        	 Boolean btExistC = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
							        	 WebElement btelemnt= driverS.findElement(By.xpath("//button[text()='Convenios']"));
							        	 
							        	 if(btelemnt.isDisplayed() &&  btExistC ) {
							        	
							        		 Thread.sleep(2000);
							        		 //existe el bt en la lista
							        		 //convenio proceso normal
							        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
						     	   		     Thread.sleep(1500); 
									        	System.out.println("Pago realizado en :Convenio");
						     	   			Thread.sleep(2000);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
								     
								            //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }else {
							        		 //buscame en Mas...
							        		 driverS.findElement(By.xpath("//button[text()='Más...']")).click();
							        		 
							        	    driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
							        	    Thread.sleep(1500); 
								        	System.out.println("Pago realizado en :Convenio");
								        	 Thread.sleep(1500);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
								        	 Thread.sleep(1500);
								            //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }

							   							        	
							        }else {
							        	
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
							        	Thread.sleep(2000);
							        	driverS.findElement(By.xpath("//button[text()='OK']")).click();
							        	Thread.sleep(2000);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");

							        	Thread.sleep(1000);
							        	//driverS.close();
							        }
					  					        	
					        }
						    
						    if(etiqueta.endsWith("Ret. renta ']")) {
					        	System.out.println("Pago realizado en :Retencion Renta");
					        
					        	Thread.sleep(2000);
					        	//ES consumidor por Convenio
					        	Boolean ErrEfecConv = driverS.findElements(By.xpath("//div[text()='Error en Método de Pago']")).size()>0;
						        
						        if(ErrEfecConv){
						        	//realizar porceso CONVENIO
						        	 Thread.sleep(1500); 
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnACEPTAR\"]")).click();
							        	System.out.println("Cambio de pago retencion renta a convenio. tipo de cons final");
							        	Thread.sleep(1500); 
							        	// buscar en la lista el bt
							        	//valida si existe el bt en la lista
							        	
							        	 Boolean btExistC = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
							        	 WebElement btelemnt= driverS.findElement(By.xpath("//button[text()='Convenios']"));
							        	 
							        	 if(btelemnt.isDisplayed() &&  btExistC ) {
							        		 //existe el bt en la lista
							        		 //convenio proceso normal
							        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
						     	   		     Thread.sleep(1500); 
									        	System.out.println("Pago realizado en :Convenio");
								        	Thread.sleep(1500);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
//=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }else {
							        		 //buscame en Mas...
							        		 driverS.findElement(By.xpath("//button[text()='Más...']")).click();
							        		 
							        	    driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
						     	   		    Thread.sleep(1500); 
								        	System.out.println("Pago realizado en :Convenio");
								        	Thread.sleep(1500);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
//=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }
							        	
							        	
						        }else {
						        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
						        	Thread.sleep(2000);
						        	driverS.findElement(By.xpath("//button[text()='OK']")).click();
						        	Thread.sleep(2000);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");

						        	Thread.sleep(1000);
						        	//driverS.close();
						        	
						        }
					        	
					        	
					        	
					        	
					        	
					        	
					
						    }
						    
						    
						 
					
			        }

			       
				
		    }
   	    		
   	    		if(caja.startsWith("B")){
//______________________________________________
   	    			
					//_____________________-----------PRIMER PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("CASERO CHULETA + SOPA + GASEOSA 12 OZ");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCount = 0;
					iCount = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCount; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicar = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicar.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					//LISTADO DE PRODUCTOS =====================================
					
					//_____________________-----------SEGUNDO PRODUCTO___________---------------______________------

   	    /*			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("JUGO DE MANGO BUF 22 OZ");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountB = 0;
					iCountB = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountB; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarB = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarB.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					
					//_____________________-----------TERCER PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("BIFFE DE CHORIZO CON PAPAS");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountC = 0;
					iCountC = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountC; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarC = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarC.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					
					//_____________________-----------CUARTO PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("COMBO BUFFALO CHICKEN");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountD = 0;
					iCountD = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountD; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarD = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarD.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
					
					//_____________________-----------QUINTO PRODUCTO___________---------------______________------

   	    			Thread.sleep(2500);
   	    		
   	    			driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    			//driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightToolbar_rightToolbar_toolbar_button3_theButton_toolbarBtnSearchCharacteristic\"]")).click();
   	    		
   	    			
   	    			
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_searchCharacteristic_searchCharacteristicTabContent_searchProductCharacteristicHeader_productFilterText\"]")).sendKeys("COMBO HAMBURGUESA POLLO");
					
					Thread.sleep(3500); 
					driverS.findElement(By.xpath("//div[@class='productIdentifier']")).click();
					
					Thread.sleep(2000); 
					
					//contar numero de etiquetas 0 / 1
					int iCountE = 0;
					iCountE = driverS.findElements(By.xpath("//div[ text()='0 / 1']")).size();
					
					Thread.sleep(1800); 
					//WebElement ceroUno = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
					//WebElement unoUno = driverS.findElement(By.xpath("//div[ text()='1 / 1']"));
				
					for(int e=0 ; e <iCountE; e++) {
						Thread.sleep(0350);
						WebElement a = driverS.findElement(By.xpath("//div[ text()='0 / 1']"));
						a.click();
						if(a.isDisplayed()) {
							Thread.sleep(0350);
							WebElement selecc =driverS.findElement(By.xpath("//div[@style='float: left; padding: 0px 0px 0px 10px;']"));
							selecc.click();
							
						}
					}
					Thread.sleep(1000); 

					WebElement btAplicarE = driverS.findElement(By.xpath("//*[@id='terminal_containerWindow_pointOfSale_OBCOMBO_Popup_body_comboFooter_closebutton']"));
					btAplicarE.click();
					System.out.println("Agregado");
					
					//_____________________------------------______________________---------------______________------
					
				*/	
					
					//==========================================================
					
					
					// ++++++  ASIGNAR CONSUMIDOR FINAL ++++++++ 
					Thread.sleep(3500); 
					WebElement asgConsumidor = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_leftPanel_receiptview_receiptheader_receiptButtons_bpbutton\"]"));
					asgConsumidor.click();
														
					//------------------ BUSCAR CONSUMIDOR FINAL -------------------------
					Thread.sleep(1000); 
					WebElement searchCons = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_modalcustomer_body_listBpsSelector_stBPAssignToReceipt_theader_modalBpSelectorScrollableHeader_filterSelector_entityFilterText\"]	"));
					searchCons.clear();//*[@id="isc_60"]
					
					// ----------- SELECIONAR CONSUMIDOR FINAL ---------------------------- 
			        String[] arr={ 
			        		 	"GANAN YANDUN LUIS EDUARDO" , 
			        			"TATA SOLUTIONS",
			        			"ALEJANDRA CRIOLLO"
			        				}; // CONSUMIDOR FINAL
			        
			        Random r=new Random();
			        int randomString=r.nextInt(arr.length);
			   
			        String nombre = arr[randomString];
					
					searchCons.sendKeys(nombre); //BUSCADOR POR TERMINO
					System.out.println("Consumidor final: " +nombre);
					Thread.sleep(5000); ////////// 
			        //click en el selecionado consumidor final
					WebElement seleccCon = driverS.findElement(By.xpath("//span[text()='"+nombre+"']"));
					Thread.sleep(6000); 
					seleccCon.click();
					// ------------FIN BUSCAR Y ASGINAR CONSUMIOR FINAL
					
					// ====== LISTO PARA SELECIONAR FORMA DE PAGO =======
					Thread.sleep(4500); 
					WebElement btTotal = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_leftToolbar_leftToolbar_toolbar_button3_theButton_btnTotalToPay\"]"));
	    	    	Thread.sleep(2500); 
	    	    	btTotal.click();
	    	    	Thread.sleep(4000);
	    	    	
	    	    	//BUSCAR LOS BOTONES EN LA LISTA 
	    	    		Boolean btA = driverS.findElements(By.xpath("//button[text()='Efectivo']")).size()>0;
				        Boolean btB = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
			        	Boolean btC = driverS.findElements(By.xpath("//button[text()='Ret. Iva ']")).size()>0;
			        	Boolean btD = driverS.findElements(By.xpath("//button[text()='Ret. renta ']")).size()>0;
	    	    	//los encuentra y agregelos al array
	    	    	
	    	    	ArrayList<String> arreglo = new ArrayList<String>();
	    	    			if(btA) {
	    	    		arreglo.add("//button[text()='Efectivo']");
	    	    	}
	    	    				if(btC) {
		    	    		arreglo.add("//button[text()='Ret. Iva ']");
		    	    	}
	    	    		
	    	    	if(btB) {
	    	    		arreglo.add("//button[text()='Convenios']");
	    	    	}
	    	    		
	    	    	
	    	    	
	    	    	if(btD) {
	    	    		arreglo.add("//button[text()='Ret. renta ']");
	    	    	}
	    	    	
	    	    	System.out.println(" FORMAS DE PAGO: ");
	    			System.out.println(arreglo);
	    	    	   	
	    	    	// CONSUMIDOR FINAL 
			        Random bts=new Random();
			        //int randomBts=bts.nextInt(arreglo.length);
			        int randomBts=bts.nextInt(arreglo.size());
			        //System.out.println(arr[randomString]);
			        String etiqueta = arreglo.get(randomBts);
			        Thread.sleep(1500);
			      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
			      //button[text()='Efectivo']
			        WebElement formPago = driverS.findElement(By.xpath(etiqueta));
			        Thread.sleep(1500);
			
		        	formPago.click();
		        	
					Thread.sleep(2000); 
					
					if(formPago.isDisplayed()) {
						//-----------------------------------------------------------------------
						 if(etiqueta.endsWith("'Efectivo']")) {
					        	System.out.println("Pago realizado en :Efectivo");
					        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
						        Thread.sleep(1500); 
						        
						        Boolean ErrEfecConv = driverS.findElements(By.xpath("//div[text()='Error en Método de Pago']")).size()>0;
					       
						        if(ErrEfecConv){
						        	//CAMBIAR FORMA DE PAGO
							        Thread.sleep(1500); 
						        	driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnACEPTAR\"]")).click();
						        	System.out.println("Cambio de pago efectivo a convenio. 'Tipo de consumidor final'");
						        	Thread.sleep(1500); 
						        	//validar si existe el bt en la lista
						        	//valida si existe el bt en la lista
						        	
						        	 Boolean btExistC = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
						        	 if(btExistC) {
						        		 System.out.println("EXISTE");
						        		 //existe en la lista el BT
						        		 Thread.sleep(1000);
						        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
					     	   		     Thread.sleep(1500); 
								       	System.out.println("Pago realizado en :Convenio");
						              	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
							        	
						              //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
						              	
						              	Thread.sleep(1500);						        
						              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
						              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	int numC = ABC+1;
						              	
						              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	
						       
						       
						              	
						              	ArrayList<String> btsExist = new ArrayList<String>();
					    	    		if(bt0) {
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
					    	    		
					    	    		}
					    	    		if(bt2) {
					    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
					    	    			

					    	    		}
					    	    	System.out.println(" FORMAS DE PAGO: ");
					    			System.out.println(btsExist);
					    	    	   	
					    	    	// CONSUMIDOR FINAL 
							        Random num=new Random();
							        //int randomBts=bts.nextInt(arreglo.length);
							        int randomBtsE=num.nextInt(btsExist.size());
							        //System.out.println(arr[randomString]);
							        String button = btsExist.get(randomBtsE);
							        Thread.sleep(1500);
							      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
							      //button[text()='Efectivo']
							        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
							        Thread.sleep(1500);
							        btACEPTAR.click();  
							        Thread.sleep(1500);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");
						        	Thread.sleep(1000);
						              	
						 //=====
						              	
							        	
						        	 
						        	 }else {
						        		 System.out.println("kcon");
						        		 //no existe en la lista el BT   MAS....
						        		 Thread.sleep(1000);
						        		 driverS.findElement(By.xpath("//button[text()='Más...']")).click();
						        		 Thread.sleep(1500);
						        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
					     	   		     Thread.sleep(1500); 
								       	System.out.println("Pago realizado en :Convenio");
						              	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
						              //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
						              	
						              	Thread.sleep(1500);						        
						              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
						              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	int numC = ABC+1;
						              	System.out.println(numC);
						              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
						              	
						       
						       
						              	
						              	ArrayList<String> btsExist = new ArrayList<String>();
					    	    		if(bt0) {
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
					    	    			System.out.println(bt0);
					    	    		}
					    	    		if(bt2) {
					    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
					    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
					    	    			System.out.println(bt2);

					    	    		}
					    	    	System.out.println(" FORMAS DE PAGO: ");
					    			System.out.println(btsExist);
					    	    	   	
					    	    	// CONSUMIDOR FINAL 
							        Random num=new Random();
							        //int randomBts=bts.nextInt(arreglo.length);
							        int randomBtsE=num.nextInt(btsExist.size());
							        //System.out.println(arr[randomString]);
							        String button = btsExist.get(randomBtsE);
							        Thread.sleep(1500);
							      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
							      //button[text()='Efectivo']
							        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
							        Thread.sleep(1500);
							        btACEPTAR.click();  
							        Thread.sleep(1500);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");
						        	Thread.sleep(1000);
						              	
						 //====
						        	 }
								       					       
						        	
						        }
						        else {
						        	//PROCESO NORMAL
						        	Thread.sleep(1000);
						    		 driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]")).click();
								     Thread.sleep(1500); 

					    	  		 WebElement btHecho = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]"));
					    	 		 btHecho.click();
							        	System.out.println("____________ Pedido realizado __________");

					    	 		 Thread.sleep(1000);
						        	// driverS.close();
						        }

				    	    					    	
					        }
							//-----------------------------------------------------------------------
						 
						 //------ convenio ---------
						 //existe convenio en la lista verifique
						 
						    if(etiqueta.endsWith("Convenios']")) {
					        	System.out.println("Pago realizado en :Convenio");
					        	Thread.sleep(1500);
					        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
					        	Thread.sleep(1500);
					        	driverS.findElement(By.xpath("//button[text()='ACEPTAR']")).click();
					        	Thread.sleep(1500);
//TALVEZ UN ERROR AKI 
//validar q el bt existe 					        	
					        	
					        	Boolean noConvenio = driverS.findElements(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnOk\"]")).size()>0;
					        	if(noConvenio) {
					        		System.out.println("EL cliente no es de tipo convenio, el pago se realizara por efectivo");
						        	Thread.sleep(1500);
					        		WebElement bt = driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnOk\"]"));
					        		bt.click();
					        		
							        	Thread.sleep(1500);
							        	driverS.findElement(By.id("terminal_containerWindow_pointOfSale_multiColumn_rightPanel_keyboard_toolbarcontainer_toolbarPayment_btnSide_OBPOS_payment.cash_button")).click();
							        	Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
								        Thread.sleep(1500); 

						    	    	
						    	 		 driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]")).click();
									     Thread.sleep(1500); 

						    	  		 WebElement btHecho = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_donebutton\"]"));
						    	 		 btHecho.click();
						    	 		 Thread.sleep(1000);
							        //	 driverS.close();
								         System.out.println("____________ Pedido realizado __________");

						    	  		 
					        		
					        	}else { ///AKIII ULTIMA MODIFICACION
					        		Thread.sleep(1500);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");
						        	Thread.sleep(1000);
						        //	driverS.close();
					        	}


					        	
					        }
						    
						    if(etiqueta.endsWith("Ret. Iva ']")) {
					        	System.out.println("Pago realizado en :retencionIva");
					        	Thread.sleep(2000);
					        	
					        	//ERROR TIPO CONVENIO
					        	 Boolean ErrEfecConv = driverS.findElements(By.xpath("//div[text()='Error en Método de Pago']")).size()>0;
							        
							        if(ErrEfecConv){
								        Thread.sleep(1500); 
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnACEPTAR\"]")).click();
							        	System.out.println("Cambio de pago ret. iva a convenio. 'Tipo de consumidor final'");
							        	Thread.sleep(1500); 
							        	//valida si existe el bt en la lista
							        	
							        	 Boolean btExistC = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
							        	 if(btExistC) {
							        		 Thread.sleep(2000);
							        		 //existe el bt en la lista
							        		 //convenio proceso normal
							        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
						     	   		     Thread.sleep(1500); 
									        	System.out.println("Pago realizado en :Convenio");
						     	   			Thread.sleep(2000);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
								     
								            //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }else {
							        		 //buscame en Mas...
							        		 driverS.findElement(By.xpath("//button[text()='Más...']")).click();
							        		 
							        	    driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
							        	    Thread.sleep(1500); 
								        	System.out.println("Pago realizado en :Convenio");
								        	 Thread.sleep(1500);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
								        	 Thread.sleep(1500);
								            //=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }

							   							        	
							        }else {
							        	
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
							        	Thread.sleep(2000);
							        	driverS.findElement(By.xpath("//button[text()='OK']")).click();
							        	Thread.sleep(2000);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");

							        	Thread.sleep(1000);
							        	//driverS.close();
							        }
					  					        	
					        }
						    
						    if(etiqueta.endsWith("Ret. renta ']")) {
					        	System.out.println("Pago realizado en :Retencion Renta");
					        
					        	Thread.sleep(2000);
					        	//ES consumidor por Convenio
					        	Boolean ErrEfecConv = driverS.findElements(By.xpath("//div[text()='Error en Método de Pago']")).size()>0;
						        
						        if(ErrEfecConv){
						        	//realizar porceso CONVENIO
						        	 Thread.sleep(1500); 
							        	driverS.findElement(By.xpath("//*[@id=\"terminal_confirmationContainer_dynamicConfirmationPopup_bodyButtons_confirmationPopup_btnACEPTAR\"]")).click();
							        	System.out.println("Cambio de pago retencion renta a convenio. tipo de cons final");
							        	Thread.sleep(1500); 
							        	// buscar en la lista el bt
							        	//valida si existe el bt en la lista
							        	
							        	 Boolean btExistC = driverS.findElements(By.xpath("//button[text()='Convenios']")).size()>0;
							        	 if(btExistC) {
							        		 //existe el bt en la lista
							        		 //convenio proceso normal
							        		 driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
						     	   		     Thread.sleep(1500); 
									        	System.out.println("Pago realizado en :Convenio");
								        	Thread.sleep(1500);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
//=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }else {
							        		 //buscame en Mas...
							        		 driverS.findElement(By.xpath("//button[text()='Más...']")).click();
							        		 
							        	    driverS.findElement(By.xpath("//button[text()='Convenios']")).click();
						     	   		    Thread.sleep(1500); 
								        	System.out.println("Pago realizado en :Convenio");
								        	Thread.sleep(1500);
								        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
//=======determino si el bt existe ya q cambia un numero devez en cuando	              	
							              	
							              	Thread.sleep(1500);						        
							              	//OBSERVAR COMO LLEGO EL BT con 0 o con 2 y con cualquiera hacer click
							              	Boolean bt0= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	int numC = ABC+1;
							              	System.out.println(numC);
							              	Boolean bt2= driverS.findElements(By.xpath("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC+"_OB.OBSSPOS.confirmReserve']")).size()>0;
							              	
							       
							       
							              	
							              	ArrayList<String> btsExist = new ArrayList<String>();
						    	    		if(bt0) {
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt0);
						    	    		}
						    	    		if(bt2) {
						    	    			//ACUM PARA INCREMENTAR EL NUMERO QUE MODIFICA DEL PEDIDO 
						    	    			btsExist.add("//button[text()='ACEPTAR' and @id='terminal_containerWindow_pointOfSale_modalpayment_bodyContent_agreementsInvoiceConnector"+numC++ +"_OB.OBSSPOS.confirmReserve']");
						    	    			System.out.println(bt2);

						    	    		}
						    	    	System.out.println(" FORMAS DE PAGO: ");
						    			System.out.println(btsExist);
						    	    	   	
						    	    	// CONSUMIDOR FINAL 
								        Random num=new Random();
								        //int randomBts=bts.nextInt(arreglo.length);
								        int randomBtsE=num.nextInt(btsExist.size());
								        //System.out.println(arr[randomString]);
								        String button = btsExist.get(randomBtsE);
								        Thread.sleep(1500);
								      //  WebElement formPago = driverS.findElement(By.xpath(etiqueta));//KIIIAISIASDIAISD
								      //button[text()='Efectivo']
								        WebElement btACEPTAR = driverS.findElement(By.xpath(button));
								        Thread.sleep(1500);
								        btACEPTAR.click();  
								        Thread.sleep(1500);
							        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
							        	System.out.println("____________ Pedido realizado __________");
							        	Thread.sleep(1000);
							              	
							 //====
							        	 }
							        	
							        	
						        }else {
						        	driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_rightPanel_toolbarpane_payment_paymentTabContent_exactbutton\"]")).click();
						        	Thread.sleep(2000);
						        	driverS.findElement(By.xpath("//button[text()='OK']")).click();
						        	Thread.sleep(2000);
						        	driverS.findElement(By.xpath("//button[text()='Hecho']")).click();
						        	System.out.println("____________ Pedido realizado __________");

						        	Thread.sleep(1000);
						        	//driverS.close();
						        	
						        }
					        	
					        	
					        	
					        	
					        	
					        	
					
						    }
						    
						    
						 
					
			        }
   	    		}
   	    		
   	    	
   	    
   	    	}
   	  // ========================3) SALIR DEL CLIENTE A LOGIN CLIENTES ===================================
	   	    	Thread.sleep(2500); 
	        	WebElement btFlecha = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_leftToolbar_leftToolbar_leftHolder_mainMenu_menuHolder_mainMenuButton\"]"));
	        	btFlecha.click();
	        	//*[@id=""]
	        	Thread.sleep(1500);
	        	WebElement btConectado = driverS.findElement(By.xpath("//*[@id=\"terminal_containerWindow_pointOfSale_multiColumn_leftToolbar_leftToolbar_leftHolder_mainMenu_menuHolder_menuScroller_connStatusButton_lbl\"]"));
	        	btConectado.click();
	        	Thread.sleep(1500);
	        	WebElement btEnd = driverS.findElement(By.xpath("//*[@id=\"terminal_profileSessionInfo_endSessionButton_lbl\"]"));
	        	btEnd.click();
	        	Thread.sleep(1500);
	        	WebElement btSalir = driverS.findElement(By.xpath("//*[@id=\"terminal_dialogsContainer_logoutDialog_bodyButtons_logoutDialogLogout\"]"));
	        	btSalir.click();

	        	Thread.sleep(35000); //fijo
	  	    	// ========================3) FIN SALIR DEL CLIENTE A LOGIN CLIENTES ===================================

				System.out.println("-----FIN DE PRUEBAS TOTAL EJECUTADAS ("+ i++ +")");
				
		}
			
        	

		
   	    		
 
	}
	
	private static void desvincular(String UrlAmbiente, String caja) throws InterruptedException {
        System.out.println("******* Desvincular dispositivo *******");
        WebDriver driver = new ChromeDriver();
        driver.get(UrlAmbiente);
        
        Thread.sleep(2500);
        System.out.println("---- Login ----");
        
        WebElement key = driver.findElement(By.id("user"));
  		  key.clear();
  		  key.sendKeys("SIDESOFT");
  		  
        WebElement password = driver.findElement(By.id("password"));
		  password.clear();
		  password.sendKeys("SSECUADOR");
                  
        WebElement loginbutton = driver.findElement(By.id("buttonOK"));
                  loginbutton.click();        
                  
                  //validacion Click btLogIN
                  if(!loginbutton.isDisplayed()){
                      System.out.println("Button LogIn isn´t click");
                  }else{
                      System.out.println("---- Fin LogIn ----");
                  }
                  Thread.sleep(20000);  
        
     //*****LISTO PARA ENTRAR AL PUNTO DE VENTA Y DESVINCULA****
				
	// Apliacion/Punto de venta/Terminal 
	WebElement aplication = driver.findElement(By.id("isc_B"));
	aplication.click();
	Thread.sleep(2000);
         //BUSCAR EL TERMINAL
	WebElement search = driver.findElement(By.name("value"));//*[@id="isc_62"]  ESTE ID CAMBIA POR ESO TOMAMOS EL NAME
        search.clear();//*[@id="isc_60"]
	search.sendKeys("Terminal Punto de Venta"); //BUSCADOR POR TERMINO
	 
		Thread.sleep(2000);
        search.sendKeys(Keys.ENTER); //ENVIE EL TEXTO A BUSCAR E INGRESE
        
        Thread.sleep(2500); 
        String urlTPV = driver.getCurrentUrl();
        System.out.println("URL Terminal Punto de venta: " + urlTPV);
        // ***** TERMINAL PUNTO DE VENTA*******//
        // ----- Buscar la caja y desvincular
        System.out.println("***** TERMINAL PUNTO DE VENTA*******");
        Thread.sleep(1000);
      
        WebElement inpNombre = driver.findElement(By.name("name"));//*[@id="isc_9E"]  EL ID CAMBIA POR ESO NAME
		inpNombre.clear();
		inpNombre.sendKeys(caja);
		inpNombre.sendKeys(Keys.ENTER);
                 
                    if(inpNombre.isDisplayed()) {
                      System.out.println("Selecionar la caja a desvincular");
                      Thread.sleep(2000);
                      
                        driver.findElement(By.xpath("//div[@eventproxy='isc_Number_0']")).click();
                                            Thread.sleep(1500);
                        driver.findElement(By.xpath("//u[text()='D']")).click();
                                            Thread.sleep(1500);
                        driver.findElement(By.xpath("//td[@class='OBFormButton' and text()='Hecho']")).click();
									  
                        System.out.println("**** Desvinculacion realizada exitosamente ****");
                        driver.close();
                      
                    }else{
                        System.out.println("--Ejecucion del checbox error--");
                    }
								         
    }
}