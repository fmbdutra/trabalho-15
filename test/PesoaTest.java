/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author fabiano.dutra
 */
public class PesoaTest {

    public static WebDriver driver;
    private final String pessoa = "C:\\Users\\fabiano.dutra\\Desktop\\trabalho-15\\src\\trabalho2-1.html";
    String esperado, resultado;
    WebElement nome, endereco, idade, salvar, aviso;
    Select sexo;
    Alert alerta;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Before
    public void inicializa() {
        driver.get(this.pessoa);

        this.nome = driver.findElement(By.id("nome"));
        this.endereco = driver.findElement(By.id("endereco"));
        this.idade = driver.findElement(By.id("idade"));
        this.sexo = new Select(driver.findElement(By.id("sexo")));
        this.salvar = driver.findElement(By.id("botao_somar"));
        this.aviso = driver.findElement(By.id("resultado"));
    }

    @Test
    public void testeNomeValoresInvalidos() {
        endereco.sendKeys("Avenida Asssis Brasil, 8750");
        idade.sendKeys("25");
        sexo.selectByValue("m");
        salvar.click();
        esperado = "Preencha o campo nome";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeNomeInvalido() {
        nome.sendKeys("Fabiano");
        endereco.sendKeys("Avenida Sertorio, 25");
        idade.sendKeys("25");
        sexo.selectByValue("m");
        salvar.click();

        alerta = driver.switchTo().alert();
        resultado = alerta.getText();
        esperado = "Cadastro realizado com sucesso";

        assertEquals(esperado, resultado);

        alerta.accept();
    }

    @Test
    public void testeEnderecoInvalido() {
        nome.sendKeys("Marcelo");
        idade.sendKeys("30");
        sexo.selectByVisibleText("Masculino");
        salvar.click();

        esperado = "Preencha o campo endereco";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeEnderecoValido() {
        nome.sendKeys("Mateus");
        endereco.sendKeys("Avenida Alexandrinho, 40");
        idade.sendKeys("88");
        sexo.selectByValue("m");
        salvar.click();

        alerta = driver.switchTo().alert();
        resultado = alerta.getText();
        esperado = "Cadastro realizado com sucesso";

        assertEquals(esperado, resultado);

        alerta.accept();
    }

    @Test
    public void testeSexoInvalido() {
        nome.sendKeys("Mateus");
        endereco.sendKeys("Avenida Alexandrinho, 40");
        idade.sendKeys("88");

        salvar.click();

        esperado = "Selecione um valor para o campo sexo";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeSexoValido() {
        nome.sendKeys("Jaqueline");
        endereco.sendKeys("Avenida Alvares Cabral, 58");
        idade.sendKeys("18");
        sexo.selectByVisibleText("Feminino");
        salvar.click();

        alerta = driver.switchTo().alert();
        resultado = alerta.getText();
        esperado = "Cadastro realizado com sucesso";

        assertEquals(esperado, resultado);

        alerta.accept();
    }

    @Test
    public void testIdadeEmBranco() {
        nome.sendKeys("Camila");
        endereco.sendKeys("Avenida General Flores Da Cunha, 1445");
        sexo.selectByValue("f");

        salvar.click();

        esperado = "Preencha o campo idade, somente com numeros";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeIdadeValorNegativo() {
        nome.sendKeys("Carlos");
        endereco.sendKeys("Avenida Tamoio, 45");
        sexo.selectByValue("m");
        idade.sendKeys("-1");

        salvar.click();

        esperado = "Preencha o campo idade com valores acima de 0";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeIdadeValorNaoNumerico() {
        nome.sendKeys("Roberto");
        endereco.sendKeys("Rua A, 5");
        sexo.selectByValue("m");
        idade.sendKeys("abc");

        salvar.click();

        esperado = "Preencha o campo idade, somente com numeros";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeIdadeCaracterEspecial() {
        nome.sendKeys("Roberto");
        endereco.sendKeys("Rua A, 5");
        sexo.selectByValue("m");
        idade.sendKeys("#$");

        salvar.click();

        esperado = "Preencha o campo idade, somente com numeros";
        resultado = aviso.getText();

        assertEquals(resultado, esperado);
    }

    @Test
    public void testeIdadeValorAcimaDeZero() {
        nome.sendKeys("Julia");
        endereco.sendKeys("R Cabral, 8");
        idade.sendKeys("40");
        sexo.selectByValue("f");
        salvar.click();

        alerta = driver.switchTo().alert();
        resultado = alerta.getText();
        esperado = "Cadastro realizado com sucesso";

        assertEquals(esperado, resultado);

        alerta.accept();
    }

    @Test
    public void testeIdadeValorIgualAZero() {
        nome.sendKeys("Juliano");
        endereco.sendKeys("R Albert Einstein, 784");
        idade.sendKeys("0");
        sexo.selectByValue("m");
        salvar.click();

        alerta = driver.switchTo().alert();
        resultado = alerta.getText();
        esperado = "Cadastro realizado com sucesso";

        assertEquals(esperado, resultado);

        alerta.accept();
    }

    @Test
    public void testeTodosValoresValidos() {
        nome.sendKeys("Taciano");
        endereco.sendKeys("R City Park, 77784");
        idade.sendKeys("49");
        sexo.selectByValue("m");
        salvar.click();

        alerta = driver.switchTo().alert();
        resultado = alerta.getText();
        esperado = "Cadastro realizado com sucesso";

        assertEquals(esperado, resultado);

        alerta.accept();
    }

    public void testeTituloDaPagina() {
        resultado = driver.getTitle();
        esperado = "Trabalho 2-1";
        
        assertEquals(esperado, resultado);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
