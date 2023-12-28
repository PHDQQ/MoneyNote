package com.duongph.moneynote.data.converter;

import androidx.annotation.NonNull;

public interface IConverter<S, D> {
    D convert(@NonNull S source);
}
