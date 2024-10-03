import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {GlobalService} from "../global.service";
import {Router} from "@angular/router";
import {OrderingService} from "./ordering.service";
import {OrderingCardComponent} from "./ordering-card/ordering-card.component";
import {NgIf} from "@angular/common";

@Component({
	selector: 'app-ordering',
	standalone: true,
	imports: [
		OrderingCardComponent,
		NgIf
	],
	templateUrl: './ordering.component.html',
})

export class OrderingComponent implements OnInit {

	orderings: any[] = [];

	constructor(
		private orderingService: OrderingService,
		private authService: AuthService,
		private global: GlobalService,
		private router: Router,
	) {
	}

	get sorted() {
		let temp = this.orderings;
		if (this.role === 'SELLER') temp = temp.filter(value => value.status !== 'WAITING');
		return temp.sort((a, b) => a.id < b.id ? 1 : 0);
	}

	ngOnInit(): void {
		this.authService.getUserProfile().add(() => {
			if (this.role !== 'USER' && this.role !== 'SELLER') this.router.navigate(['/login']);
		});

		this.orderingService.findAll().subscribe({
			next: ((res: any) => {
				this.orderings = res.data;
			}),
			error: ((e) => {
				console.log(e.error);
			})
		})
	}

	get role(): string {
		return this.global.role;
	}

}
