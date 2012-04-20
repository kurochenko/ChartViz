package net.kurochenko.chartviz.backend.parser.ecb;

/**
 * @author Andrej Kuročenko <kurochenko@mail.muni.cz>
 */
public interface ExchangeRateParser {
    public ExchangeRateDTO parseAll();
    public ExchangeRateDTO parseActual();
}
