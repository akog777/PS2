import model.Assento;
import model.AssentoEconomica;
import model.AssentoExecutiva;
import model.AssentoPrimeiraClasse;
import model.Passageiro;

public class App {
    public static void main(String[] args) {
        Assento a1 = new AssentoEconomica("Economica", 50.0);
        //System.out.println("Valor do Assento: "+ a1.calcularPrecoFinal());

        Assento a2 = new AssentoExecutiva("Executiva", 50.0);
        //System.out.println("Valor do Assento: "+ a2.calcularPrecoFinal()); 

        Assento a3 = new AssentoPrimeiraClasse("Primeira Classe", 50.0);
        //System.out.println("Valor do Assento: "+ a3.calcularPrecoFinal()); 

        Passageiro p1 = new Passageiro("Ronaldinho");
        System.out.println("Comprando assento para a classe economica.");
        p1.setAssento(a1);

        System.out.println("O assento do Passageiro ["+p1.getNome()+"] custa [R$"+p1.getAssento().calcularPrecoFinal()+"]");

        System.out.println("Realizando o upgrade para a classe executiva.");
        p1.setAssento(a2);
        System.out.println("O assento do Passageiro ["+p1.getNome()+"] custa [R$"+p1.getAssento().calcularPrecoFinal()+"]");

        System.out.println("Realizando o upgrade para a primeira classe.");
        p1.setAssento(a3);
        System.out.println("O assento do Passageiro ["+p1.getNome()+"] custa [R$"+p1.getAssento().calcularPrecoFinal()+"]");

    }
}