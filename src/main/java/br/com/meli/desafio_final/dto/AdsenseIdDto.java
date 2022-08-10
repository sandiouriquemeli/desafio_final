package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Adsense;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class AdsenseIdDto {
    private Long id;

    public AdsenseIdDto(Adsense adsense) {
        this.id = adsense.getId();
    }

    public static List<AdsenseIdDto> convertDto(List<Adsense> listAdsense) {
        return listAdsense.stream().map(AdsenseIdDto::new).collect(Collectors.toList());
    }
}
