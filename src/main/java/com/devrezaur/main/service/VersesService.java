package com.devrezaur.main.service;

import de.evangeliumstaucher.VersesApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VersesService {
    private final VersesApi versesApi;
}
