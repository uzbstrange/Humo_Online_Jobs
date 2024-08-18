document.getElementById('jobSearchForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const title = document.getElementById('jobTitle').value;
    const jobResults = document.getElementById('jobResults');
    jobResults.innerHTML = '';

    fetch(`/api/jobs?title=${title}`)
        .then(response => response.json())
        .then(jobs => {
            if (jobs.length > 0) {
                jobs.forEach(job => {
                    const div = document.createElement('div');
                    div.innerHTML = `<h3>${job.title}</h3><p>${job.description}</p>`;
                    jobResults.appendChild(div);
                });
            } else {
                jobResults.innerHTML = '<p>No jobs found</p>';
            }
        })
        .catch(error => console.error('Error fetching jobs:', error));
});
