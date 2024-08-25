package com.marketplace.product.img;

import com.marketplace.product.Product;
import com.marketplace.system.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static com.marketplace.util.Global.saveFile;

@Service
@RequiredArgsConstructor
public class ProductImgService {

    private final ProductImgRepository repository;

    public void save(MultipartFile file, Product product) {
        String img;

        try {
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                img = saveFile(file, "product");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new BadRequestException("Некорректное изображение");
        }

        repository.save(new ProductImg(img, product));
    }

}
