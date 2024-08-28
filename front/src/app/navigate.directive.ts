import {Directive, HostListener, Input} from '@angular/core';
import {NavigationExtras, Router} from "@angular/router";

@Directive({
	selector: '[appNavigate]',
	standalone: true
})
export class NavigateDirective {

	@Input() navigateURL: string = '';
	@Input() queryParams: any;

	constructor(
		private router: Router,
	) {
	}

	@HostListener('click', ['$event'])
	@HostListener('auxclick', ['$event'])
	onClick(event: MouseEvent) {
		const navigationExtras: NavigationExtras = {
			queryParams: this.queryParams
		};

		if (event.ctrlKey || event.metaKey || event.button == 1) {
			const urlWithParams = this.router.createUrlTree([this.navigateURL], navigationExtras).toString();
			window.open(urlWithParams, '_blank');
			event.preventDefault();
		} else {
			this.router.navigate([this.navigateURL], navigationExtras);
		}
	}
}
