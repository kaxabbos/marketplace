import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "../global.service";

@Injectable({
	providedIn: 'root'
})

export class ProductImgService {

	constructor(
		private http: HttpClient,
		private global: GlobalService,
	) {
	}

	save(productId: number, files: any) {
		let formData = new FormData();
		for (let i = 0; i < files.length; i++) {
			formData.append('files', files[i], files[i].name);
		}
		return this.http.post(
			this.global.backendURL + `/products/${productId}/img`,
			formData,
			{
				headers: this.global.headersMultipartToken,
			}
		)
	}

	delete(productId: number, productImgId: number) {
		return this.http.delete(
			this.global.backendURL + `/products/${productId}/img/${productImgId}`,
			{
				headers: this.global.headersToken,
			}
		)
	}
}
