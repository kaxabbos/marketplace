import {Injectable} from '@angular/core';
import {GlobalService} from "../global.service";
import {HttpClient, HttpParams} from "@angular/common/http";
import {BehaviorSubject} from "rxjs";

@Injectable({
	providedIn: 'root'
})

export class ProductService {

	productSubject = new BehaviorSubject<any>({
		products: [],
	});

	constructor(
		private global: GlobalService,
		private http: HttpClient,
	) {
	}

	public findAll() {
		this.http.get(
			this.global.backendURL + '/products',
		).subscribe({
			next: ((res: any) => {
				this.productSubject.next({
					...this.productSubject,
					products: res.data,
				});
			})
		})
	}

	public findAllByBind(bind: string, ownerId: number) {
		return this.http.get(
			this.global.backendURL + '/products/bind',
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({
					bind: bind,
					ownerId: ownerId,
				})
			}
		)
	}

	public findById(id: number) {
		return this.http.get(
			this.global.backendURL + `/products/${id}`,
			{headers: this.global.headersToken}
		)
	}

	public save(product: any) {
		return this.http.post(
			this.global.backendURL + `/products`,
			product,
			{
				headers: this.global.headersJsonToken,
				params: new HttpParams().appendAll({category: product.category}),
			}
		)
	}

	public update(id: number, product: any) {
		return this.http.put(
			this.global.backendURL + `/products/${id}`,
			product,
			{
				headers: this.global.headersJsonToken,
				params: new HttpParams().appendAll({category: product.category}),
			}
		)
	}

	public delete(id: number) {
		return this.http.delete(
			this.global.backendURL + `/products/${id}`,
			{headers: this.global.headersToken}
		)
	}

	public active(id: number) {
		return this.http.get(
			this.global.backendURL + `/products/${id}/active`,
			{headers: this.global.headersToken}
		)
	}

	public refine(id: number, refine: string) {
		return this.http.get(
			this.global.backendURL + `/products/${id}/refine`,
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({refine: refine})
			}
		)
	}

	public waiting(id: number) {
		return this.http.get(
			this.global.backendURL + `/products/${id}/waiting`,
			{headers: this.global.headersToken}
		)
	}

	public archive(id: number) {
		return this.http.get(
			this.global.backendURL + `/products/${id}/archive`,
			{headers: this.global.headersToken}
		)
	}

}
