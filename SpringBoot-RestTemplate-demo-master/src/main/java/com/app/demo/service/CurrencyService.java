package com.app.demo.service;

import com.app.demo.entity.CurrencyModel;
import com.app.demo.repository.CurrencyModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyService {
    @Autowired
    private CurrencyModelRepository currencyModelRepository;

    public List<CurrencyModel> query(){

        List<CurrencyModel> currencies = new ArrayList<CurrencyModel>();
        currencyModelRepository.findAll().forEach(currencies::add);

        return currencies;
    }

    public CurrencyModel insert(CurrencyModel currency){
        CurrencyModel _currency = currencyModelRepository.save(new CurrencyModel(currency.getCur(), currency.getCurdesc(), currency.getExchangerate()));
        return _currency;
    }

    public CurrencyModel update(long id, CurrencyModel currency){
        Optional<CurrencyModel> currencylData = currencyModelRepository.findById(id);
        CurrencyModel _currency = new CurrencyModel();
        if (currencylData.isPresent()) {
            _currency = currencylData.get();
            _currency.setCur(currency.getCur());
            _currency.setCurdesc(currency.getCurdesc());
            _currency.setExchangerate(currency.getExchangerate());
            return currencyModelRepository.save(_currency);
        }
        return null;
    }

    public void delete(long id){
        currencyModelRepository.deleteById(id);
    }
}
