
    // Function to update the container's height
    function updateContainerHeight() {
    // Get the current scroll position
    $(".speedscroll").each(function (ind, container) {
        let top = container.getBoundingClientRect().top; //container.offsetTop;
        //console.log(container.id+ ": top " + top);
        if (top < 0) return;
        // Calculate the new height based on the scroll position
        let newpadding = top * 30 / window.innerHeight;
        newpadding += 10;
        //newpadding = Math.round(newpadding);
        //console.log(container.id+ ": newpadding " + newpadding);
        newpadding = newpadding > 2 ? newpadding : 0;
        let newpaddingText = `${newpadding}%`;
        //console.log(container.id+ ": newpaddingText " + newpaddingText)
        // Update the container's height
        if (container.style.paddingTop != newpaddingText) {
            container.style.paddingTop = newpaddingText;
            container.offsetTop = container.offsetTop;
        }
    })
}

    // Listen for the scroll event
    window.addEventListener('scroll', updateContainerHeight);
    updateContainerHeight()
