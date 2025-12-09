package co.edu.poli.cloudapp.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import co.edu.poli.cloudapp.dto.ProfesorDTO;
import co.edu.poli.cloudapp.entities.Profesor;
import co.edu.poli.cloudapp.repositories.IProfesorRepository;
import co.edu.poli.cloudapp.services.IProfesorService;

@Service
@Transactional
public class ProfesorServiceImpl implements IProfesorService{

    @Autowired
    private final IProfesorRepository repoProfesor;
    private final ModelMapper modelMapper;

    public ProfesorServiceImpl(IProfesorRepository repository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.repoProfesor = repository;
    }


    @Override
    public ProfesorDTO create(ProfesorDTO profesorDTO) {
        Profesor entidad = modelMapper.map(profesorDTO, Profesor.class);
        Profesor saved = repoProfesor.save(entidad);

        return modelMapper.map(saved, ProfesorDTO.class);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ProfesorDTO> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfesorDTO findById(Long id) {
        Profesor p = repoProfesor.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        return modelMapper.map(p, ProfesorDTO.class);
    }

    @Override
    public ProfesorDTO update(Long id, ProfesorDTO profesorDTO) {
        Profesor existente = repoProfesor.findById(id).orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        existente.setNomProfesor(profesorDTO.getNomProfesor());
        existente.setApeProfesor(profesorDTO.getApeProfesor());
        existente.setFechaNacimiento(profesorDTO.getFechaNacimiento());
        existente.setEmail(profesorDTO.getEmail());

        Profesor saved = repoProfesor.save(existente);
        return modelMapper.map(saved, ProfesorDTO.class);
    }


}
