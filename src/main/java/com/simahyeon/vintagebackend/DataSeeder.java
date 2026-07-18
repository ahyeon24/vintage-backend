package com.simahyeon.vintagebackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final StyleTagRepository styleTagRepository;

    public DataSeeder(StyleTagRepository styleTagRepository) {
        this.styleTagRepository = styleTagRepository;
    }

    @Override
    public void run(String... args) {
        List<String> tagNames = List.of(
                "유러피안", "아메리칸", "일본빈티지", "밀리터리",
                "Y2K", "럭셔리빈티지", "데님", "스트릿빈티지", "혼합·다양"
        );

        for (String name : tagNames) {
            if (styleTagRepository.findByName(name).isEmpty()) {
                styleTagRepository.save(new StyleTag(name));
            }
        }
    }
}