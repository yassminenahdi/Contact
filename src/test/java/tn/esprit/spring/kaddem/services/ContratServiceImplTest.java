package tn.esprit.spring.kaddem.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import java.util.Optional;
import java.util.List;
//package tn.esprit.spring.kaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Specialite;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;




@ExtendWith(MockitoExtension.class)
public class ContratServiceImplTest {
    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    private Contrat contrat;

    @BeforeEach
    public void setUp() {
        contrat = new Contrat();
        contrat.setIdContrat(1);
        contrat.setDateDebutContrat(new Date());
        contrat.setDateFinContrat(new Date());
        contrat.setArchive(false);
        contrat.setMontantContrat(1000);
    }



    @Test
    public void testAddContrat() {
        Contrat contrat = new Contrat(new Date(), new Date(), Specialite.IA, false, 1000);
        when(contratRepository.save(any(Contrat.class))).thenReturn(contrat);

        Contrat result = contratService.addContrat(contrat);

        assertNotNull(result);
        assertEquals(contrat.getMontantContrat(), result.getMontantContrat());
        verify(contratRepository, times(1)).save(contrat);
    }

    @Test
    public void testRetrieveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Contrat result = contratService.retrieveContrat(1);

        assertNotNull(result);
        assertEquals(1, result.getIdContrat());
        verify(contratRepository, times(1)).findById(1);
    }
    @Test
    public void testRemoveContrat() {
        Contrat contrat = new Contrat();
        contrat.setIdContrat(1);
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        contratService.removeContrat(1);

        verify(contratRepository, times(1)).delete(contrat);
    }

    @Test
    public void testCalculerPrime_ContratExistant() {
        // Arrange
        int contratId = 1;
        int age = 30;
        int montant = 1000;

        Contrat contrat = new Contrat();
        contrat.setMontantContrat(montant);

        when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));


        double prime = contratService.calculerPrime(contratId, age);

        assertEquals(1300.0, prime, 0.01);
    }

    @Test
    public void testCalculerPrime_ContratIntrouvable() {

        int contratId = 1;
        int age = 30;

        when(contratRepository.findById(contratId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> contratService.calculerPrime(contratId, age));
    }

    @Test
    public void testCalculerPrime_MontantInvalide() {
        int contratId = 1;
        int age = 30;
        int montant = -500;

        Contrat contrat = new Contrat();
        contrat.setMontantContrat(montant);

        when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));

        assertThrows(IllegalArgumentException.class, () -> contratService.calculerPrime(contratId, age));
    }


}


