package com.example.hardware_softwareshopping.service.implementations;

import com.example.hardware_softwareshopping.model.Raport;
import com.example.hardware_softwareshopping.repository.UserRepository;
import com.example.hardware_softwareshopping.service.RaportService;
import com.example.hardware_softwareshopping.utils.FileExporter;
import com.example.hardware_softwareshopping.utils.XMLFileExporter;

import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RaportServiceImplementation implements RaportService {
    private final UserRepository userRepository;

    public RaportServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String exportData() {

        FileExporter fileExporter = new XMLFileExporter();
        Integer nr = userRepository.findAll().size();
        Raport raport = new Raport().builder().currentData(new Date())
                .noUsers(nr).build();
        return fileExporter.exportData(raport);

    }
}
