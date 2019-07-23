package com.vesta.integration.floor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.common.UtilData;
import com.vesta.controller.view.FloorView;
import com.vesta.integration.IntegrationConfigTest;
import com.vesta.repository.FloorRepository;
import com.vesta.repository.entity.FloorEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class  FloorIntegrationTest extends IntegrationConfigTest {

    @Autowired
    FloorRepository floorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser
    @Test
    public void getListOfFloors() throws Exception {
        // given
        FloorEntity floorEntity = UtilData.floorEntity();
        floorRepository.save(floorEntity);

        // when
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/floors")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<FloorView> response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<List<FloorView>>() {
                });

        // then
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertThat(response.get(0).getId(), is(1L));
        assertThat(response.get(0).getName(), is(floorEntity.getName()));
    }
}