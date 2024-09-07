import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {GlobalService} from "../global.service";

@Injectable({
	providedIn: 'root'
})
export class OrderingService {

	constructor(
		private http: HttpClient,
		private global: GlobalService,
	) {
	}

	findAll() {
		return this.http.get(
			this.global.backendURL + '/orderings',
			{headers: this.global.headersToken}
		)
	}

	save(count: number, productId: number) {
		return this.http.post(
			this.global.backendURL + '/orderings',
			"",
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({count: count, productId: productId}),
			}
		)
	}

	update(count: number, orderingId: number) {
		return this.http.patch(
			this.global.backendURL + `/orderings/${orderingId}`,
			"",
			{
				headers: this.global.headersToken,
				params: new HttpParams().appendAll({count: count}),
			}
		)
	}

	ordered(orderingId: number) {
		return this.http.get(
			this.global.backendURL + `/orderings/${orderingId}/ordered`,
			{
				headers: this.global.headersToken,
			}
		)
	}

	done(orderingId: number) {
		return this.http.get(
			this.global.backendURL + `/orderings/${orderingId}/done`,
			{
				headers: this.global.headersToken,
			}
		)
	}

	rejected(orderingId: number) {
		return this.http.get(
			this.global.backendURL + `/orderings/${orderingId}/rejected`,
			{
				headers: this.global.headersToken,
			}
		)
	}

	delivered(orderingId: number) {
		return this.http.get(
			this.global.backendURL + `/orderings/${orderingId}/delivered`,
			{
				headers: this.global.headersToken,
			}
		)
	}

	accepted(orderingId: number) {
		return this.http.get(
			this.global.backendURL + `/orderings/${orderingId}/accepted`,
			{
				headers: this.global.headersToken,
			}
		)
	}

}
