package com.diegoliveiraa.locadora_filme.servives;

import com.diegoliveiraa.locadora_filme.dtos.RenterDTO;
import com.diegoliveiraa.locadora_filme.entitys.Renter;
import com.diegoliveiraa.locadora_filme.infra.RenterStatus;
import com.diegoliveiraa.locadora_filme.repositories.RenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenterService {

    @Autowired
    private RenterRepository renterRepository;

    public Renter createRenter(RenterDTO data) {

        Renter newRenter = new Renter(data);

        this.renterRepository.save(newRenter);

        return newRenter;
    }

    public List<Renter> getAllRenter() {
        return this.renterRepository.findAll();
    }

    public Renter updateRenter(RenterDTO data) {

        Renter updateRenter = this.findByDocument(data.document());

        updateRenter.setFirstname(data.firstname());
        updateRenter.setLastname(data.lastname());
        updateRenter.setDocument(data.document());
        updateRenter.setPhone(data.phone());
        updateRenter.setRenterStatus(data.renterStatus());
        updateRenter.setCep(data.cep());
        updateRenter.setAddress(data.address());
        updateRenter.setNumber(data.number());
        updateRenter.setComplement(data.complement());
        updateRenter.setCity(data.city());
        updateRenter.setBirthday(data.birthday());

        this.renterRepository.save(updateRenter);

        return updateRenter;
    }

    public void deleteRenter(String id) {
        Renter deleteRenter = this.findById(id);

        this.renterRepository.delete(deleteRenter);
    }

    public Renter findById(String id) {
        return this.renterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public Renter findByDocument(String document) {

        return this.renterRepository.findByDocument(document)
                .orElseThrow(() -> new RuntimeException("Client not found!"));
    }

    public void checkRenterStatus(String id) {
        Renter renter = this.findById(id);

        if (renter.getRenterStatus() == RenterStatus.INATIVE) {
            throw new IllegalArgumentException("Renter is not authorized!");
        }
    }
}
