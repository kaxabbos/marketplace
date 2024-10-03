import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {GlobalService} from "../global.service";
import {BehaviorSubject} from "rxjs";

@Injectable({
	providedIn: 'root'
})
export class CategoryService {

	categorySubject = new BehaviorSubject<any>({
		categories: []
	});

	constructor(
		private http: HttpClient,
		private global: GlobalService,
	) {
	}

	findAll() {
		this.http.get(
			this.global.backendURL + '/categories',
		).subscribe({
			next: ((res: any) => {
				this.categorySubject.next({
					...this.categorySubject.value,
					categories: res.data,
				})
			}),
			error: ((e: any) => {
				console.log(e.error)
			})
		})
	}
}
