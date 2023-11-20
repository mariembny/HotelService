package com.Hotel.HotelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    // Injection de dépendance via le constructeur
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        return optionalHotel.orElse(null);
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        // Ajout de validation ou de logique de création si nécessaire
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Long id, Hotel hotel) {
        Optional<Hotel> optionalExistingHotel = hotelRepository.findById(id);

        if (optionalExistingHotel.isPresent()) {
            Hotel existingHotel = optionalExistingHotel.get();
            // Copier les propriétés non nulles de hotel vers existingHotel
            BeanUtils.copyProperties(hotel, existingHotel, "id");
            return hotelRepository.save(existingHotel);
        } else {
            // Gérer le cas où l'hôtel n'est pas trouvé
            return null;
        }
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
