package de.evangeliumstaucher.app.service;

import de.evangeliumstaucher.ChaptersApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChaptersService {
    private final ChaptersApi chaptersApi;
}
