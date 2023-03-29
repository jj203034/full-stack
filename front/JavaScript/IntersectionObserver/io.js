const sections = Array.from(document.body.querySelectorAll(':scope > [rel="section"]'));
const sectionIo = new IntersectionObserver(e => {
    e.forEach(x => {
        if (x.isIntersecting) {
            x.target.classList.add('visible');
        } else {
            x.target.classList.remove('visible');
        }
    });
}, {
    threshold: 0.5
});
sections.forEach(section => sectionIo.observe(section));
sections.forEach(section => {
    const children = section.querySelectorAll(':scope > *');
    const tDelayFactor = 375;
    let tDelay = tDelayFactor;
    children.forEach(child => {
        child.style.transitionDelay = tDelay + 'ms';
        tDelay += tDelayFactor;
    });
    if (section.classList.contains('skills')) {
        const skillContainer = section.querySelector(':scope > .skill-container');
        const skills = Array.from(skillContainer.querySelectorAll(':scope > .skill'));
        let skillDelay = tDelayFactor / 2;
        skills.forEach(skill => {
            skill.style.transitionDelay = skillDelay + 'ms';
            skillDelay += tDelayFactor / 2;
        });
    }
});