package br.com.meli.desafio_final.dto;

import br.com.meli.desafio_final.model.entity.Adsense;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdsenseIdDto {
    private Long id;

    public AdsenseIdDto(Adsense adsense) {
        this.id = adsense.getId();
    }

    public static List<AdsenseIdDto> convertDto(List<Adsense> listAdsense) {
        return listAdsense.stream().map(AdsenseIdDto::new).collect(Collectors.toList());
    }
}
