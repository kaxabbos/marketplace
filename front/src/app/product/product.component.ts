import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {GlobalService} from "../global.service";
import {NgIf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
	selector: 'app-product',
	standalone: true,
	imports: [
		NgIf
	],
	templateUrl: './product.component.html',
})
export class ProductComponent implements OnInit {

	constructor(
		private authService: AuthService,
		private global: GlobalService,
		public router: Router,
	) {
	}

	ngOnInit(): void {
		this.authService.getUserProfile();
	}

	getRole() {
		return this.global.role;
	}

}
