public class CriterioPrecoMaximo implements CriterioBusca {
    public boolean testar(Produto p, String valor) {
        double precoMaximo = Double.parseDouble(valor);
        return p.getPreco() <= precoMaximo;
    }

}
