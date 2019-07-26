package com.vesta.mock.subject;

import com.vesta.common.UtilData;
import com.vesta.exception.VestaException;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.service.SubjectTemplateService;
import com.vesta.service.converter.SubjectTemplateConverter;
import com.vesta.service.dto.SubjectTemplateDto;
import com.vesta.service.impl.SubjectTemplateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class SubjectTemplateTest {

    @Mock
    private SubjectTemplateService service;

    @Mock
    private SubjectTemplateRepository repository;

    @Mock
    private SubjectTemplateConverter converter;

    @Before
    public void setUp() {
        service = new SubjectTemplateServiceImpl(repository, converter);
    }

    @Test
    public void getById_Valid() {
        // given
        SubjectTemplateEntity subjectTemplateEntity = UtilData.subjectImageEntity();

        // when
        Mockito.when(repository.findById(subjectTemplateEntity.getId()))
                .thenReturn(Optional.of(subjectTemplateEntity));

        // then
        service.getById(subjectTemplateEntity.getId());
        verify(repository).findById(subjectTemplateEntity.getId());
    }

    @Test(expected = VestaException.class)
    public void getById_Invalid() {
        service.getById(null);
    }

    @Test
    public void deleteById_Succes() {
        // given
        SubjectTemplateEntity subjectTemplateEntity = UtilData.subjectImageEntity();
        // when
        service.deleteImage(subjectTemplateEntity.getId());
        // then
        verify(repository).deleteById(subjectTemplateEntity.getId());
    }

    @Test
    public void addImage_Success() {
        // given
        SubjectTemplateDto subjectTemplateDto = new SubjectTemplateDto();
        subjectTemplateDto.setImage("test-image");

        service.createImage(subjectTemplateDto);
    }

    @Test
    public void findAll_Images() {
        // given
        SubjectTemplateEntity subjectTemplateEntity = UtilData.subjectImageEntity();
        // when
        Mockito.when(repository.findAll())
                .thenReturn(List.of(subjectTemplateEntity));
        service.getAll();
    }
}
