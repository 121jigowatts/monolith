package com.jigowatts.monolith.domain.model.criteria;

import lombok.Getter;

@Getter
public class HitPerPage {
    private int value;
    private static final String NAME = "hit_per_page";

    public HitPerPage(String value) {
        if (value == null || value.isEmpty()) {
            this.value = 10;
        } else {
            try {
                this.value = Integer.parseInt(value);
            } catch (Exception e) {
                throw new IllegalArgumentException(NAME + "=" + value);
            }

            if (this.value > 100) {
                throw new IllegalArgumentException(NAME + "=" + value);
            }
        }
    }
}
