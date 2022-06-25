document.querySelectorAll('.land').forEach(item => {
  		item.addEventListener('click', event => {
    		Android.showToast(item.id);
  		})
	})