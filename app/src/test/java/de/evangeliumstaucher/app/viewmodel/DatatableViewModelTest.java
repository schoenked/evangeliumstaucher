package de.evangeliumstaucher.app.viewmodel;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static com.google.common.truth.Truth.assertThat;

class DatatableViewModelTest {

    @Test
    public void testLanguage() {
        assertThat(DatatableViewModel.getLanguage(Locale.of("asdf"))).isEqualTo("English");
        assertThat(DatatableViewModel.getLanguage(Locale.GERMAN)).isEqualTo("German");
        assertThat(DatatableViewModel.getLanguage(Locale.GERMANY)).isEqualTo("German");
        assertThat(DatatableViewModel.getLanguage(Locale.CHINESE)).isEqualTo("Chinese");
    }

}