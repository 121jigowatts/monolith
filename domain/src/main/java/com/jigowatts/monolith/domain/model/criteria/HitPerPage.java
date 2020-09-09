package com.jigowatts.monolith.domain.model.criteria;

import lombok.Getter;

@Getter
public class HitPerPage {
    private int value;

    public HitPerPage(String value) {
        if (value == null || value.isEmpty()) {
            this.value = 10;
        } else {
            try {
                this.value = Integer.parseInt(value);
            } catch (Exception e) {
                throw new IllegalArgumentException(value);
            }

            if (this.value > 100) {
                throw new IllegalArgumentException(value);
            }
        }
    }
}
