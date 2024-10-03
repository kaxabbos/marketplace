import {Component, Input} from '@angular/core';
import {NavigateDirective} from "../../navigate.directive";
import {FormsModule} from "@angular/forms";
import {OrderingService} from "../ordering.service";
import {DecimalPipe, NgIf} from "@angular/common";
import {OrderingComponent} from "../ordering.component";
import {GlobalService} from "../../global.service";
import {AlertService} from "../../alert/alert.service";

@Component({
	selector: 'app-ordering-card',
	standalone: true,
	imports: [
		NavigateDirective,
		FormsModule,
		NgIf,
		DecimalPipe
	],
	templateUrl: './ordering-card.component.html',
})
export class OrderingCardComponent {

	@Input() ordering: any;

	constructor(
		private orderingService: OrderingService,
		private orderingComponent: OrderingComponent,
		private global: GlobalService,
		private alert: AlertService,
	) {
	}

	update() {
		this.orderingService.update(this.ordering.count, this.ordering.id).subscribe({
			next: ((res: any) => {
				this.ordering = res.data;
			}),
			error: ((e) => {
				console.log(e.error);
				this.alert.showAlertMessage(e.error.message)
			})
		})
	}

	checkOwner() {
		return this.global.userid == this.ordering.ownerId;
	}

	checkSeller() {
		return this.global.userid == this.ordering.product.ownerId;
	}

	statusError(e: any) {
		console.log(e.error);
		this.alert.showAlertMessage(e.error.message)
	}

	ordered() {
		this.orderingService.ordered(this.ordering.id).subscribe({
			next: (res: any) => {
				this.ordering = res.data;
			},
			error: ((e) => {
				this.statusError(e);
			})
		})
	}

	done() {
		this.orderingService.done(this.ordering.id).subscribe({
			next: (res: any) => {
				this.ordering = res.data;
			},
			error: ((e) => {
				this.statusError(e);
			})
		})
	}

	rejected() {
		this.orderingService.rejected(this.ordering.id).subscribe({
			next: (res: any) => {
				this.ordering = res.data;
			},
			error: ((e) => {
				this.statusError(e);
			})
		})
	}

	delivered() {
		this.orderingService.delivered(this.ordering.id).subscribe({
			next: (res: any) => {
				this.ordering = res.data;
			},
			error: ((e) => {
				this.statusError(e);
			})
		})
	}

	accepted() {
		this.orderingService.accepted(this.ordering.id).subscribe({
			next: (res: any) => {
				this.ordering = res.data;
			},
			error: ((e) => {
				this.statusError(e);
			})
		})
	}

}
