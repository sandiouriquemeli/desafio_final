package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Adsense;
import br.com.meli.desafio_final.model.entity.Product;
import br.com.meli.desafio_final.model.entity.Seller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AdsenseDto {

    private Seller seller;

    private Product product;

    private Double price;

    public AdsenseDto(Adsense adsense) {
        this.seller = adsense.getSeller();
        this.product = adsense.getProduct();
        this.price = adsense.getPrice();
    }

    public static List<AdsenseDto> convertDto(List<Adsense> listAdsense) {
        return listAdsense.stream().map(AdsenseDto::new).collect(Collectors.toList());
    }
}
